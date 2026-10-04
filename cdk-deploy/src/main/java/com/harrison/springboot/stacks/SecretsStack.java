package com.harrison.springboot.stacks;

import com.harrison.springboot.resources.GlobalConfiguration;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.secretsmanager.Secret;
import software.amazon.awscdk.services.secretsmanager.SecretStringGenerator;
import software.constructs.Construct;

public class SecretsStack extends Stack {
    public static final String DB_USERNAME = "springboot";

    public SecretsStack(final Construct scope, final String id) {
        super(scope, id);

        Secret dbPasswordSecret = Secret.Builder.create(this, "SpringBootCourseDBPasswordSecret")
                .secretName("springboot-course/db-password")
                .description("Secret used as the RDS Cluster password for general access")
                .generateSecretString(
                        SecretStringGenerator.builder()
                                .secretStringTemplate(String.format("{\"%s\": \"%s\"}", "username", DB_USERNAME))
                                .generateStringKey("password")
                                .excludePunctuation(true)
                                .build())
                .build();

        CfnOutput.Builder.create(this, "RdsSecretArn")
                .value(dbPasswordSecret.getSecretArn())
                .exportName(GlobalConfiguration.BASE_RESOURCES_RDS_SECRET_ARN)
                .build();
    }

}
