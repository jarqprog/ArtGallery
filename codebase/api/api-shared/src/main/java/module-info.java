module api.shared {
    exports com.jarqprog.commonapi.absmodel;
    exports com.jarqprog.commonapi.constants;
    exports com.jarqprog.commonapi.components;
    exports com.jarqprog.commonapi.exceptions;
    exports com.jarqprog.commonapi;

    requires domain.shared;

    requires lombok;
    requires java.persistence;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires spring.web;
    requires spring.context;
    requires java.xml.bind;
    requires spring.beans;
    requires spring.tx;
    requires spring.jdbc;
    requires spring.orm;
    requires java.sql;
    requires modelmapper;
    requires java.transaction;
    requires slf4j.api;
    requires domain.person;
    requires domain.art;
    requires spring.security.core;
}