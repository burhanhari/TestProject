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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prop;

    }
}
