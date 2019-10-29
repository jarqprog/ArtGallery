package com.jarqprog.artGallery.api.infrastructure.config.databaseConfig;

import java.util.Properties;

public interface DatabaseConfig {

    String getDriverClass();
    String getUrl();
    String getUser();
    String getPassword();
    Properties getJPAProperties();
}
