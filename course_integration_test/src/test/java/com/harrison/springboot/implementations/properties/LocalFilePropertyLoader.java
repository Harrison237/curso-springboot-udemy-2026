package com.harrison.springboot.implementations.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import com.harrison.springboot.interfaces.PropertyLoader;

public class LocalFilePropertyLoader implements PropertyLoader {
    @Override
    public void loadProperties(Map<String, String> storage) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            if (input == null) {
                throw new IllegalStateException("No se encontró app.properties en el classpath de pruebas");
            }
            properties.load(input);

            for (String key : properties.stringPropertyNames()) {
                storage.put(key, properties.getProperty(key));
            }
        } catch (IOException exception) {
            throw new IllegalStateException("No fue posible leer app.properties", exception);
        }
    }
}
