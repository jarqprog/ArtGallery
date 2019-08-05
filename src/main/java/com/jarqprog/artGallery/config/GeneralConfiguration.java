package com.jarqprog.artGallery.config;

public class GeneralConfiguration implements AppConfiguration {

    private final static String APPLICATION_PACKAGE = "com.jarqprog.artGallery";

    @Override
    public String getApplicationPackage() {
        return APPLICATION_PACKAGE;
    }
}
