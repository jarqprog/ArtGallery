package com.jarqprog.artGallery.config.repository.database;

public interface DatabaseConfig {

    String getDriverClass();
    String getUrl();
    String getUser();
    String getPassword();
    String getHibernateDialect();
}
