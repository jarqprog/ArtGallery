package com.jarqprog.artGallery.config.data.database;

public class H2Config implements DatabaseConfig {

    private final static String DRIVER_CLASS = "org.h2.Driver";
    private final static String URL = "jdbc:h2:mem:test";
    private final static String USER = "sa";
    private final static String PASSWORD = "sa";
    private final static String HIBERNATE_DIALECT = "org.hibernate.dialect.H2Dialect";

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
    public String getHibernateDialect() {
        return HIBERNATE_DIALECT;
    }
}
