package com.jarqprog.artGallery.config.persistenceConfig;

public interface DatabaseConfig {

    String getDriverClass();
    String getUrl();
    String getUser();
    String getPassword();
    String getHibernateDialect();
}
