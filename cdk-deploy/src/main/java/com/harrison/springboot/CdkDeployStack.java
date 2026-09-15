package com.harrison.springboot;

import software.constructs.Construct;

import com.harrison.springboot.resources.GlobalResources;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;
import software.amazon.awscdk.services.ecr.Repository;
import software.amazon.awscdk.services.ecs.Cluster;
import software.amazon.awscdk.services.ecs.FargateTaskDefinition;

public class CdkDeployStack extends Stack {
    public CdkDeployStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public CdkDeployStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);
        
        Cluster cluster = Cluster.Builder.create(this, "SpringBootCourseECSCluster")
                .vpc(GlobalResources.getDefaultVPC(this))
                .clusterName("springboot-course-cluster")
                .build();
    }
}
