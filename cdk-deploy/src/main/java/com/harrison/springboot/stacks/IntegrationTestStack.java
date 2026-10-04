package com.harrison.springboot.stacks;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.ssm.StringParameter;
import software.constructs.Construct;

public class IntegrationTestStack extends Stack {
    public IntegrationTestStack(final Construct scope, final String id) {
        super(scope, id);

        StringParameter apiUsernameParam = StringParameter.Builder.create(this, "SpringBootCourseApiUsernameParam")
                .parameterName("/integration/test/username/param")
                .description("Username for correct login meant to be used in integration tests")
                .stringValue("admin")
                .build();

        StringParameter apiPasswordParam = StringParameter.Builder.create(this, "SpringBootCourseApiPasswordParam")
                .parameterName("/integration/test/password/param")
                .description("Password for correct login meant to be used in integration tests")
                .stringValue("12345")
                .build();

        StringParameter apiIncorrectUsernameParam = StringParameter.Builder.create(this, "SpringBootCourseApiIncorrectUsernameParam")
                .parameterName("/integration/test/incorrect/username/param")
                .description("Incorrect Username for correct login meant to be used in integration tests")
                .stringValue("anyUserText")
                .build();

        StringParameter apiIncorrectPasswordParam = StringParameter.Builder.create(this, "SpringBootCourseApiIncorrectPasswordParam")
                .parameterName("/integration/test/incorrect/password/param")
                .description("Incorrect Password for correct login meant to be used in integration tests")
                .stringValue("incorrectPassword")
                .build();
    }
}
