package com.harrison.springboot.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class AppConfiguration {
    private final Properties properties = new Properties();
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

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            if (input == null) {
                throw new IllegalStateException("No se encontró app.properties en el classpath de pruebas");
            }
            properties.load(input);
        } catch (IOException exception) {
            throw new IllegalStateException("No fue posible leer app.properties", exception);
        }
    }

    private String requiredProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Falta la propiedad obligatoria: " + key);
        }
        return value;
    }
}
