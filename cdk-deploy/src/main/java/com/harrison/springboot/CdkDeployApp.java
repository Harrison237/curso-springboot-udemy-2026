package com.harrison.springboot;

import software.amazon.awscdk.Acknowledgment;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.Validations;

import com.harrison.springboot.models.ECSServiceStackProps;
import com.harrison.springboot.stacks.BaseResourcesStack;
import com.harrison.springboot.stacks.ECSServiceStack;

public class CdkDeployApp {
    public static void main(final String[] args) {
        App app = new App();

        Validations.of(app).acknowledge(
                Acknowledgment.builder()
                        .id("CloudFormation-Validate::E1151")
                        .reason("Local AWS Emulator uses vpc-default as a valid VPC id")
                        .build(),
                Acknowledgment.builder()
                        .id("CloudFormation-Validate::E1154")
                        .reason("Local AWS Emulator uses subnet-default-<identifier> as valid subnet names")
                        .build());

        Environment env = Environment.builder()
                .account("000000000000")
                .region("us-east-1")
                .build();

        StackProps generalStackProps = StackProps.builder()
                .env(env)
                .build();

        try {
            BaseResourcesStack baseResourcesStack = new BaseResourcesStack(app, "BaseResourcesStack",
                    generalStackProps);

            ECSServiceStackProps ecsStackServiceProps = new ECSServiceStackProps(
                    baseResourcesStack.getPrivateSubnets(),
                    // baseResourcesStack.getLbSecurityGroup(),
                    // baseResourcesStack.getDbConnectionString(),
                    baseResourcesStack.getDbUsername(),
                    baseResourcesStack.getDbPassword());
            new ECSServiceStack(app, "ECSServiceStack", generalStackProps, ecsStackServiceProps);
        } catch (Exception e) {
            // TODO: handle exception
        }

        app.synth();
    }
}
