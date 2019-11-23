package com.jarqprog.artGallery.api.infrastructure.config.databaseConfig;

import com.jarqprog.artGallery.api.ApiConstants;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Profile(ApiConstants.PROD_PROFILE)
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
    public Properties getJPAProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");//"create-drop" "none"
        properties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        properties.setProperty("spring.h2.console.enabled", "true");
        properties.setProperty("spring.jpa.generate-ddl", "true");
        return properties;
    }
}