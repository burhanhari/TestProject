package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesLoader {

    private static final Properties PROPERTIES = loadProperties();

    private PropertiesLoader() {
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = PropertiesLoader.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            if (input == null) {
                throw new IllegalStateException("Could not find config/config.properties on the classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Could not load config/config.properties", e);
        }

        applySystemPropertyOverride(properties, "browser");
        applySystemPropertyOverride(properties, "url");
        applySystemPropertyOverride(properties, "username");
        applySystemPropertyOverride(properties, "password");
        applySystemPropertyOverride(properties, "env");

        String environment = properties.getProperty("env");
        if (environment != null && !environment.trim().isEmpty()) {
            String environmentUrl = properties.getProperty("url." + environment.trim());
            if (environmentUrl != null && !environmentUrl.trim().isEmpty()) {
                properties.setProperty("url", environmentUrl.trim());
            }
        }

        return properties;
    }

    private static void applySystemPropertyOverride(Properties properties, String key) {
        String value = System.getProperty(key);
        if (value != null && !value.trim().isEmpty()) {
            properties.setProperty(key, value.trim());
        }
    }
}
