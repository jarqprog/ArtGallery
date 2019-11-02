package com.jarqprog.artGallery.api.infrastructure.config.databaseConfig;


import com.jarqprog.artGallery.api.ApiConstants;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Profile(ApiConstants.TEST_PROFILE)
public class H2TestConfig implements DatabaseConfig {

    private final static String DRIVER_CLASS = "org.h2.Driver";
    private final static String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private final static String USER = "sa";
    private final static String PASSWORD = "";
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
    public Properties getJPAProperties() {
        Properties properties = new Properties();
        properties.setProperty("javax.persistence.schema-generation.database.action", "drop-and-create");
        properties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        properties.setProperty("spring.h2.console.enabled", "false");
        properties.setProperty("spring.jpa.generate-ddl", "true");
        return properties;
    }

}
