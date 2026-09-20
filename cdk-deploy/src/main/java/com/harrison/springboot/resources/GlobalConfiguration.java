package com.harrison.springboot.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class GlobalConfiguration {

    private GlobalConfiguration() {

    }

    public static final String AWS_REGION = "us-east-1";
    public static final String AWS_ACCOUNT_ID = "000000000000";
    // TODO: Fix Sonar Hint java:S1170
    public static final String AWS_ENDPOINT_URL = "http://192.168.1.10:4566";
    public static final String STACK_NAME = "SpringBootCourseCdkStack";
    public static final String BASE_RESOURCES_ALB_SG_EXPORT_NAME = "SpringBootCourseAlbSecurityGroupId";
    public static final String BASE_RESOURCES_ECS_SG_EXPORT_NAME = "SpringBootCourseEcsSecurityGroupId";
    public static final String BASE_RESOURCES_RDS_CONNECTION_STRING = "SpringBootCourseRdsConnectionString";

    public static String getPublicKeyPath() throws IOException {
        return Files.readString(Path.of(System.getProperty("user.home"), ".ssh", "floci-ec2.pub")).trim();
    }
}
