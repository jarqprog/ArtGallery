package com.jarqprog.personapi.configuration;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static com.jarqprog.commonapi.constants.ApiConstants.HERO_PROFILE;


@Component
@Profile(HERO_PROFILE)
class ConfigPostgres implements ConfigDatabase {

    private final static String DRIVER_CLASS = "org.postgresql.Driver";
    private final static String URL = System.getenv("JDBC_DATABASE_URL");
    private final static String USER = System.getenv("JDBC_DATABASE_USERNAME");
    private final static String PASSWORD = System.getenv("JDBC_DATABASE_PASSWORD");
    private final static String HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

    @Override
    public String getDriverClass() {
        return DRIVER_CLASS;
    }

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public String getUser() {
        return USER;
    }

    @Override
    public String getPassword() {
        return PASSWORD;
    }

    @Override
    public Properties getJPAProperties() {
        Properties properties = new Properties();
        // Hibernate ddl auto (create, create-drop, validate, update)
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        properties.setProperty("spring.jpa.generate-ddl", "true");
        return properties;
     }
}