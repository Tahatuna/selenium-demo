package org.seleniumdemo.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        loadProperties("src/test/resources/config.properties");
    }

    public static void loadProperties(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + filePath);
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            throw new IllegalStateException("Properties file not loaded!");
        }
        return properties.getProperty(key);
    }
}
