package com.harrison.springboot.resources;

import software.amazon.awscdk.services.ec2.IVpc;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ec2.VpcLookupOptions;
import software.constructs.Construct;

public abstract class GlobalResources {
    private GlobalResources() {
    }

    public static IVpc getDefaultVPC(Construct scope) {
        return Vpc.fromLookup(scope, "DefaultVpc",
                VpcLookupOptions.builder()
                        .isDefault(true)
                        .build());
    }
}
