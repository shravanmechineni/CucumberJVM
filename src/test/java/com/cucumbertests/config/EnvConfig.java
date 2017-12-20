package com.cucumbertests.config;

import com.cucumbertests.util.ConfigDetector;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class EnvConfig {

    private static final Properties PROPS = new Properties();

    static {
        String envProperties = String.format("env/%s.properties", ConfigDetector.getFrom());
        InputStream input = EnvConfig.class.getClassLoader().getResourceAsStream(envProperties);
        try {
            PROPS.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Problem reading properties file", e);
        }
    }

    private static String getProperty(String key) {
        String env = ConfigDetector.getEnv();
        String envKey = String.format("%s.%s", env, key);
        return getRawProperty(envKey);
    }

    private static String getRawProperty(String key) {
        String value = PROPS.getProperty(key);
        Objects.requireNonNull(value, "No such config entry: " + key);
        return value;
    }

    public static String getUrlFor(String url) {
        String configEntry = String.format("%s.url", url);
        return EnvConfig.getProperty(configEntry);
    }
}
