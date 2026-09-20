package com.harrison.springboot.stacks;

import java.util.List;

import com.harrison.springboot.models.ECSStackServiceProps;
import com.harrison.springboot.resources.GlobalResources;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.IVpc;
import software.amazon.awscdk.services.ec2.SubnetSelection;
import software.amazon.awscdk.services.ecs.CapacityProviderStrategy;
import software.amazon.awscdk.services.ecs.Cluster;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.FargateService;
import software.amazon.awscdk.services.ecs.ContainerDefinitionOptions;
import software.amazon.awscdk.services.ecs.FargateTaskDefinition;
import software.amazon.awscdk.services.ecs.LoadBalancerTargetOptions;
import software.amazon.awscdk.services.elasticloadbalancingv2.AddApplicationTargetGroupsProps;
import software.amazon.awscdk.services.elasticloadbalancingv2.ApplicationListener;
import software.amazon.awscdk.services.elasticloadbalancingv2.ApplicationLoadBalancer;
import software.amazon.awscdk.services.elasticloadbalancingv2.ApplicationProtocol;
import software.amazon.awscdk.services.elasticloadbalancingv2.ApplicationTargetGroup;
import software.amazon.awscdk.services.elasticloadbalancingv2.BaseApplicationListenerProps;
import software.amazon.awscdk.services.elasticloadbalancingv2.HealthCheck;
import software.amazon.awscdk.services.elasticloadbalancingv2.TargetType;
import software.constructs.Construct;

public class ECSServiceStack extends Stack {
    public ECSServiceStack(final Construct scope, final String id, StackProps props,
            ECSStackServiceProps specificProps) {
        super(scope, id, props);

        IVpc globalVpc = GlobalResources.getDefaultVPC(this);

        Cluster cluster = Cluster.Builder.create(this, "SpringBootCourseECSCluster")
                .clusterName("springboot-course-cluster")
                .vpc(globalVpc)
                .build();

        FargateTaskDefinition taskDefinition = FargateTaskDefinition.Builder
                .create(this, "SpringBootCourseTaskDefinition")
                .cpu(512)
                .memoryLimitMiB(1024)
                .build();

        taskDefinition.addContainer("springboot-course-app", ContainerDefinitionOptions.builder()
                .image(ContainerImage
                        .fromRegistry("192.168.1.10:5101/000000000000/us-east-1/springboot-course-repository:v1"))
                .build());

        FargateService service = FargateService.Builder.create(this, "SpringBootCourseFargateService")
                .vpcSubnets(SubnetSelection.builder().subnets(specificProps.privateSubnets()).build())
                .cluster(cluster)
                .taskDefinition(taskDefinition)
                .minHealthyPercent(100)
                .desiredCount(1)
                .capacityProviderStrategies(List.of(
                        CapacityProviderStrategy.builder()
                                .capacityProvider("FARGATE_SPOT")
                                .weight(2)
                                .build(),
                        CapacityProviderStrategy.builder()
                                .capacityProvider("FARGATE")
                                .weight(1)
                                .base(1)
                                .build()))
                .build();

        ApplicationLoadBalancer alb = ApplicationLoadBalancer.Builder.create(this, "SpringBootCourseECSALB")
                .vpc(globalVpc)
                .internetFacing(true)
                .securityGroup(specificProps.lbSecurityGroup())
                .build();

        ApplicationListener listener = alb.addListener("SpringBootCourseECSALBListener",
                BaseApplicationListenerProps.builder()
                        .port(80)
                        .protocol(ApplicationProtocol.HTTP)
                        .build());

        ApplicationTargetGroup targetGroup = ApplicationTargetGroup.Builder
                .create(this, "SpringBootCourseECSTargetGroup")
                .vpc(globalVpc)
                .protocol(ApplicationProtocol.HTTP)
                .port(8080)
                .targetType(TargetType.IP)
                .healthCheck(
                        HealthCheck.builder()
                                .path("/actuator/health")
                                .healthyHttpCodes("200")
                                .build())
                .build();

        targetGroup.addTarget(
                service.loadBalancerTarget(
                        LoadBalancerTargetOptions.builder()
                                .containerName("springboot-course-app")
                                .containerPort(8080)
                                .build()));

        listener.addTargetGroups("SpringBootCourseECSALBListenerTargetGroup", AddApplicationTargetGroupsProps.builder()
                .targetGroups(List.of(targetGroup))
                .build());
    }
}
