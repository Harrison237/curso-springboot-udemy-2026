package com.harrison.springboot.models;

import java.util.List;

import software.amazon.awscdk.services.ec2.Subnet;

public record ECSServiceStackProps(
    List<Subnet> privateSubnets,
    // Se comenta debido a limitaciones de Floci al momento de resolver las referencias entre instancias como Stack Outputs. Se deben utilizar Stack Outputs puros en su lugar
    // SecurityGroup lbSecurityGroup,
    //String databaseConnectionString,
    String databaseUsername,
    String databasePassword
) {

}
