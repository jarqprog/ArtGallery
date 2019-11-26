package com.jarqprog.commonapi;

import java.util.Properties;

public interface DatabaseConfig {

    String getDriverClass();
    String getUrl();
    String getUser();
    String getPassword();
    Properties getJPAProperties();
}
