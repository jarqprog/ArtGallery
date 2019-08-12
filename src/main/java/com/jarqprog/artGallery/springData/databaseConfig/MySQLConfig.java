package com.jarqprog.artGallery.springData.databaseConfig;

public class MySQLConfig implements DatabaseConfig {

    private final static String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/art_gallery?" +
            "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL8Dialect";


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
