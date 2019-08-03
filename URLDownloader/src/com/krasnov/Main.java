package com.krasnov;

import java.io.File;

public class Main {
    static String directory = "";
    public static void main(String[] args) {
        Url url = new Url(args[0]);
        try {
            if (isDirectoryExist(args[1])) {
                directory = args[1];
                System.out.println(String.format("File will be saved in %s", directory));
            }
            else {
                System.out.println("Path does not exist. "
                                +"File will be saved in project directory");
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File will be saved in project directory");
        }
        System.out.println(url.getProtocol());
        System.out.println(url.getName());
        System.out.println(url.getDomain());
        System.out.println(url.getMapping());
    }
    private static boolean isDirectoryExist(String path) {
        File file = new File(path);
        if (file.isDirectory())
            return true;
        return false;
    }
}