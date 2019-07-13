package com.krasnov;

public class Parser {
    public static String getProtocol(String url) {
        try {
            String[] parsed = url.split("://");
            return parsed[0];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    public static String getName(String url) {
        try {
            String[] parsed = url.split("://");
            parsed = parsed[1].split("\\.");
            return parsed[0];
        }
        catch (ArrayIndexOutOfBoundsException ex){
            return null;
        }
    }
    public static String getDomain(String url) {
        try {
            String[] parsed = url.split("://");
            parsed = parsed[1].split("\\.");
            parsed = parsed[1].split("/");
            return parsed[0];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    public static String getMapping(String url) {
        try {
            String[] parsed = url.split("://");
            parsed = parsed[1].split("\\.");
            parsed = parsed[1].split("/");
            parsed = parsed[1].split("\\?");
            return parsed[0];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
}
