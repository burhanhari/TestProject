package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    public static Properties getProperties() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            prop.load(fis);

            // Allow Jenkins/CLI system properties to override config values
            applySystemPropertyOverride("browser");
            applySystemPropertyOverride("url");
            applySystemPropertyOverride("username");
            applySystemPropertyOverride("password");
            applySystemPropertyOverride("env");

            // If env is set and a matching url.<env> exists, override url
            String env = System.getProperty("env");
            if (env != null && !env.trim().isEmpty()) {
                String envKey = "url." + env.trim();
                String envUrl = prop.getProperty(envKey);
                if (envUrl != null && !envUrl.trim().isEmpty()) {
                    prop.setProperty("url", envUrl.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prop;

    }

    private static void applySystemPropertyOverride(String key) {
        String value = System.getProperty(key);
        if (value != null && !value.trim().isEmpty()) {
            prop.setProperty(key, value.trim());
        }
    }
}
