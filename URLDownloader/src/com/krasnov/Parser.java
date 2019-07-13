package com.krasnov;

public class Parser {
    public static String getProtocol(String url) {
        String[] parsed = url.split("://");
        return parsed[0];
    }
    public static String getName(String url) {
        String[] parsed = url.split("://");
        parsed = parsed[1].split("\\.");
        return parsed[0];
    }
    public static String getDomain(String url) {
        String[] parsed = url.split("://");
        parsed = parsed[1].split("\\.");
        parsed = parsed[1].split("/");
        return parsed[0];
    }
}
