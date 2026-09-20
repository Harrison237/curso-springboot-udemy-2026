package com.harrison.springboot.models;

import java.util.List;

import software.amazon.awscdk.services.ec2.SecurityGroup;
import software.amazon.awscdk.services.ec2.Subnet;

public record ECSStackServiceProps(
    List<Subnet> privateSubnets,
    SecurityGroup lbSecurityGroup,
    String databaseConnectionString
) {

}
