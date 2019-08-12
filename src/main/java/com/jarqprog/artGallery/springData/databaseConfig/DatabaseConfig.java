package com.jarqprog.artGallery.springData.databaseConfig;

public interface DatabaseConfig {

    String getDriverClass();
    String getUrl();
    String getUser();
    String getPassword();
    String getHibernateDialect();
}
