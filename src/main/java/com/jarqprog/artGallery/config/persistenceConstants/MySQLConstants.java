package com.jarqprog.artGallery.config.persistenceConstants;

public interface MySQLConstants {

    String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://localhost:3306/art_gallery?" +
            "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String USER = "root";
    String PASSWORD = "root";
    String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL8Dialect";

}
