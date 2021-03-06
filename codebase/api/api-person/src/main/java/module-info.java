module api.person {
    exports com.jarqprog.personapi.contact;
    exports com.jarqprog.personapi.contact.dto;
    exports com.jarqprog.personapi.user;
    exports com.jarqprog.personapi.user.dto;
    exports com.jarqprog.personapi.roleUser;

    requires api.shared;
    requires domain.person;

    requires lombok;
    requires java.persistence;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires spring.web;
    requires spring.context;
    requires spring.tx;
    requires org.apache.commons.lang3;

    requires java.validation;
    requires slf4j.api;
    requires spring.beans;
    requires spring.webmvc;
    requires java.xml.bind;
    requires org.hibernate.orm.core;
    requires java.transaction;
    requires spring.core;
    requires spring.security.core;
    requires spring.orm;
    requires java.sql;
    requires spring.jdbc;
    requires javax.servlet.api;
    requires modelmapper;
    requires domain.shared;
}