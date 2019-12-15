package com.jarqprog.commonapi.constants;

public interface ApiConstants {

    String VERSION = "v2";
    String BASE_URL_PATH = "/api/" + VERSION + "/";


    //profiles
    String DEV_PROFILE = "dev";
    String PROD_PROFILE = "prod";
    String TEST_PROFILE = "test";
    String HERO_PROFILE = "hero";//Heroku

    String JSON_LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
}
