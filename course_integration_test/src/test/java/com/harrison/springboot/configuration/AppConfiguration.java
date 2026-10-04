package com.harrison.springboot.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.harrison.springboot.implementations.properties.AWSParameterStorePropertyLoader;
import com.harrison.springboot.implementations.properties.LocalFilePropertyLoader;
import com.harrison.springboot.interfaces.PropertyLoader;

public final class AppConfiguration {
    private final Map<String, String> propertyStorage = new HashMap<>();
    private static final List<PropertyLoader> propertyLoaders = List.of(
        new LocalFilePropertyLoader(),
        new AWSParameterStorePropertyLoader()
    );
    private static AppConfiguration instance = null;

    private AppConfiguration() {
        loadProperties();
    }

    public static AppConfiguration getInstance() {
        if (instance == null) instance = new AppConfiguration();

        return instance;
    }

    public String baseUri() {
        return requiredProperty("base.uri");
    }

    public String contentType() {
        return requiredProperty("app.content.type");
    }

    public String loginUsername() {
        return requiredProperty("app.username");
    }

    public String loginPassword() {
        return requiredProperty("app.password");
    }

    public String incorrectLoginUsername() {
        return requiredProperty("app.incorrect.username");
    }

    public String incorrectLoginPassword() {
        return requiredProperty("app.incorrect.password");
    }

    private void loadProperties() {
        for (PropertyLoader loader : propertyLoaders) {
            loader.loadProperties(propertyStorage);
        }
    }

    private String requiredProperty(String key) {
        String value = propertyStorage.getOrDefault(key, "");
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Falta la propiedad obligatoria: " + key);
        }
        return value;
    }
}
