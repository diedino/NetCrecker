package com.krasnov;

public class Main {

    public static void main(String[] args) {
        Url url = new Url(args[0]);
        System.out.println(url.getProtocol());
        System.out.println(url.getName());
        System.out.println(url.getDomain());
    }
}