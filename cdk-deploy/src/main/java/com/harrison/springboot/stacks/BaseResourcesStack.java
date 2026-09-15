package com.harrison.springboot.stacks;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.ecr.Repository;
import software.constructs.Construct;

public class BaseResourcesStack extends Stack {
    public BaseResourcesStack(final Construct scope, final String id) {
        super(scope, id, null);

        Repository repository = Repository.Builder.create(this, "SpringBootCourseRepository")
                .repositoryName("springboot-course-repository")
                .build();
    }
}
