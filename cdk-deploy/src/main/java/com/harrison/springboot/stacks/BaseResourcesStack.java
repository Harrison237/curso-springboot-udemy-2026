package com.harrison.springboot.stacks;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.harrison.springboot.resources.GlobalConfiguration;
import com.harrison.springboot.resources.GlobalResources;

import lombok.Getter;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.CfnParameter;
import software.amazon.awscdk.SecretValue;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.Token;
import software.amazon.awscdk.services.rds.AuroraMysqlClusterEngineProps;
import software.amazon.awscdk.services.rds.AuroraMysqlEngineVersion;
import software.amazon.awscdk.services.rds.ClusterInstance;
import software.amazon.awscdk.services.rds.Credentials;
import software.amazon.awscdk.services.rds.DatabaseCluster;
import software.amazon.awscdk.services.rds.DatabaseClusterEngine;
import software.amazon.awscdk.services.rds.ProvisionedClusterInstanceProps;
import software.amazon.awscdk.services.rds.SubnetGroup;
import software.amazon.awscdk.services.ec2.IKeyPair;
import software.amazon.awscdk.services.ec2.IMachineImage;
import software.amazon.awscdk.services.ec2.IVpc;
import software.amazon.awscdk.services.ec2.Instance;
import software.amazon.awscdk.services.ec2.InstanceType;
import software.amazon.awscdk.services.ec2.KeyPair;
import software.amazon.awscdk.services.ec2.MachineImage;
import software.amazon.awscdk.services.ec2.Peer;
import software.amazon.awscdk.services.ec2.Port;
import software.amazon.awscdk.services.ec2.SecurityGroup;
import software.amazon.awscdk.services.ec2.Subnet;
import software.amazon.awscdk.services.ec2.SubnetSelection;
import software.amazon.awscdk.services.ec2.SubnetType;
import software.amazon.awscdk.services.ecr.Repository;
import software.constructs.Construct;

public class BaseResourcesStack extends Stack {

    @Getter
    private final List<Subnet> privateSubnets;

    @Getter
    private final SecurityGroup lbSecurityGroup;

    @Getter
    private final String dbConnectionString;

    public static final String DB_USERNAME = "springboot";

    private static final String STRING_TEXT = "String";

    @Getter
    private final String dbPassword = System.getenv("DB_PASSWORD");

    public BaseResourcesStack(final Construct scope, final String id, StackProps props) throws IOException {
        super(scope, id, props);

        IVpc globalVpc = GlobalResources.getDefaultVPC(this);
        IMachineImage generalAmi = MachineImage
                .genericLinux(Map.of(props.getEnv().getRegion(), "ami-0abcdef1234567891"));

        SecurityGroup albSg = SecurityGroup.Builder.create(this, "SpringBootCourseALBSecurityGroup")
                .securityGroupName("springboot-course-alb-sg")
                .vpc(globalVpc)
                .allowAllOutbound(true)
                .build();
        albSg.addIngressRule(Peer.anyIpv4(), Port.tcp(80), "Allow HTTP traffic from Internet");

        SecurityGroup ecsSg = SecurityGroup.Builder.create(this, "SpringBootCourseECSSecurityGroup")
                .vpc(globalVpc)
                .description("Security group for ECS Fargate tasks")
                .allowAllOutbound(true)
                .build();
        ecsSg.addIngressRule(albSg, Port.tcp(8080));

        SecurityGroup bastionSg = SecurityGroup.Builder.create(this, "SpringBootCourseBastionSecurityGroup")
                .securityGroupName("springboot-course-bastion-sg")
                .vpc(globalVpc)
                .allowAllOutbound(true)
                .build();

        SecurityGroup rdsSg = SecurityGroup.Builder.create(this, "SpringBootCourseRDSSecurityGroup")
                .securityGroupName("springboot-course-rds-sg")
                .vpc(globalVpc)
                .allowAllOutbound(true)
                .build();
        rdsSg.addIngressRule(ecsSg, Port.tcp(3306), "Allow ECS tasks to access RDS MySQL");
        rdsSg.addIngressRule(bastionSg, Port.tcp(3306), "Allow Bastion Instance to access RDS MySQL");

        CfnParameter developmentClientCidr = CfnParameter.Builder.create(this, "DevelopmentClientCidr")
                .type(STRING_TEXT)
                .description("CIDR block permitted to connect to the bastion host via SSH")
                .build();

        bastionSg.addIngressRule(
                Peer.ipv4(developmentClientCidr.getValueAsString()),
                Port.tcp(22),
                "SSH from development client");

        CfnParameter privateSubnetACidr = CfnParameter.Builder.create(this, "PrivateSubnetACidr")
                .type(STRING_TEXT)
                .description("CIDR block for private subnet A")
                .build();

        CfnParameter privateSubnetBCidr = CfnParameter.Builder.create(this, "PrivateSubnetBCidr")
                .type(STRING_TEXT)
                .description("CIDR block for private subnet B")
                .build();

        Subnet privateSubnetA = Subnet.Builder.create(this, "PrivateSubnetA")
                .vpcId(globalVpc.getVpcId())
                .cidrBlock(privateSubnetACidr.getValueAsString())
                .availabilityZone("us-east-1a")
                .mapPublicIpOnLaunch(false)
                .build();

        Subnet privateSubnetB = Subnet.Builder.create(this, "PrivateSubnetB")
                .vpcId(globalVpc.getVpcId())
                .cidrBlock(privateSubnetBCidr.getValueAsString())
                .availabilityZone("us-east-1b")
                .mapPublicIpOnLaunch(false)
                .build();

        SubnetGroup dbPrivateSubnetGroup = SubnetGroup.Builder.create(this, "DBPrivateSubnetGroup")
                .subnetGroupName("db-private-subnet-group")
                .description("Private subnet group for DB Instance")
                .vpc(globalVpc)
                .vpcSubnets(SubnetSelection.builder().subnets(List.of(privateSubnetA, privateSubnetB)).build())
                .build();

        Repository.Builder.create(this, "SpringBootCourseECRRepository")
                .repositoryName("springboot-course-repository")
                .build();

        DatabaseCluster cluster = DatabaseCluster.Builder.create(this, "SpringBootCourseDBCluster")
                .clusterIdentifier("springboot-course-db-cluster")
                .engine(DatabaseClusterEngine.auroraMysql(AuroraMysqlClusterEngineProps
                        .builder().version(AuroraMysqlEngineVersion.VER_3_12_0).build()))
                .credentials(
                        Credentials.fromPassword(DB_USERNAME, SecretValue.unsafePlainText(dbPassword)))
                .writer(
                        ClusterInstance.provisioned("writer", ProvisionedClusterInstanceProps.builder()
                                .publiclyAccessible(false)
                                .build()))
                .vpc(globalVpc)
                .subnetGroup(dbPrivateSubnetGroup)
                .port(3306)
                .securityGroups(Arrays.asList(rdsSg))
                .build();

        IKeyPair sshKeyPair = KeyPair.fromKeyPairName(this, "ImportedKeyPair", "springboot-course-bastion-key");

        Instance.Builder.create(this, "SpringBootCourseBastion")
                .vpc(globalVpc)
                .vpcSubnets(SubnetSelection.builder()
                        .subnetType(SubnetType.PUBLIC)
                        .build())
                .instanceType(new InstanceType("t3.micro"))
                .machineImage(generalAmi)
                .securityGroup(bastionSg)
                .keyPair(sshKeyPair)
                .associatePublicIpAddress(true)
                .build();

        privateSubnets = List.of(
                privateSubnetA,
                privateSubnetB);

        String rdsPort = Token.asString(cluster.getClusterEndpoint().getPort());

        CfnOutput.Builder.create(this, "AlbSecurityGroupId")
                .value(albSg.getSecurityGroupId())
                .exportName(GlobalConfiguration.BASE_RESOURCES_ALB_SG_EXPORT_NAME)
                .build();

        CfnOutput.Builder.create(this, "EcsSecurityGroupId")
                .value(ecsSg.getSecurityGroupId())
                .exportName(GlobalConfiguration.BASE_RESOURCES_ECS_SG_EXPORT_NAME)
                .build();

        CfnOutput.Builder.create(this, "RdsEndpoint")
                .value(String.format(
                        "jdbc:mysql://%s:%s/%s",
                        cluster.getClusterEndpoint().getHostname(),
                        rdsPort,
                        "db_jpa_crud?createDatabaseIfNotExist=true"))
                .exportName(GlobalConfiguration.BASE_RESOURCES_RDS_CONNECTION_STRING)
                .build();

        lbSecurityGroup = albSg;
        dbConnectionString = String.format(
                "jdbc:mysql://%s:%s",
                cluster.getClusterEndpoint().getHostname(),
                "3306");
    }
}
