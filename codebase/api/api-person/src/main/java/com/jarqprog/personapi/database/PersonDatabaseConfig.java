package com.jarqprog.personapi.database;

import com.jarqprog.commonapi.DatabaseConfig;
import org.springframework.stereotype.Component;

@Component
public interface PersonDatabaseConfig extends DatabaseConfig {

    String DATABASE_NAME = "person_db";

}
