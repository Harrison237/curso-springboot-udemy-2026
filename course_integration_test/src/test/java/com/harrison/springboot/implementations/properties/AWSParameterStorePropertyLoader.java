package com.harrison.springboot.implementations.properties;

import java.util.Map;

import com.harrison.springboot.interfaces.PropertyLoader;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParametersByPathRequest;

public class AWSParameterStorePropertyLoader implements PropertyLoader {
    private static final String AWS_PARAMETER_STORE_PERFIX = "/integration/test";

    @Override
    public void loadProperties(Map<String, String> storage) {
        try (SsmClient ssm = SsmClient.builder()
                .region(Region.US_EAST_1)
                .build()) {
            GetParametersByPathRequest req = GetParametersByPathRequest.builder()
                    .path(AWS_PARAMETER_STORE_PERFIX)
                    .recursive(true)
                    .withDecryption(true)
                    .build();

            ssm.getParametersByPathPaginator(req)
                    .stream()
                    .flatMap(response -> response.parameters().stream())
                    .forEach(p -> {
                        String key = p.name().replaceFirst("/", "")
                                .replace("integration/test", "app")
                                .replace("/param", "")
                                .replace("/", ".");
                        storage.put(key, p.value());
                    });
        }
    }
}
