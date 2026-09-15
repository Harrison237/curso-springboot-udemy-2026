package com.harrison.springboot;

import software.amazon.awscdk.App;
import com.harrison.springboot.stacks.BaseResourcesStack;

public class CdkDeployApp {
    public static void main(final String[] args) {
        App app = new App();

        new BaseResourcesStack(app, "BaseResourcesStack");

        // new CdkDeployStack(app, GlobalConfiguration.STACK_NAME, StackProps.builder()
        //         .env(env)
        //         .build());

        app.synth();
    }
}

