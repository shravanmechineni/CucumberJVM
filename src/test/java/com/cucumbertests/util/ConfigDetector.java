package com.cucumbertests.util;

public class ConfigDetector {

    public static String getEnv() {
        String env = ((null != System.getProperty("env.name")) ? System.getProperty("env.name") : "prod").toLowerCase();
        return env;
    }

    public static String getFrom() {
        return ((null != System.getProperty("from")) ? System.getProperty("from") : "local").toLowerCase();
    }

}
