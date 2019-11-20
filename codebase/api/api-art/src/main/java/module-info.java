module api.art {
    exports com.jarqprog.artapi.domains.author;
    exports com.jarqprog.artapi.domains.author.dto;
    exports com.jarqprog.artapi.domains.picture;
    exports com.jarqprog.artapi.domains.picture.dto;
    exports com.jarqprog.artapi.domains.commentary;
    exports com.jarqprog.artapi.domains.commentary.dto;
    exports com.jarqprog.artapi;

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
    requires domain.art;
    requires api.common;
    requires spring.webmvc;
    requires java.xml.bind;
    requires spring.jdbc;
    requires spring.orm;
    requires java.sql;
    requires javax.servlet.api;
}