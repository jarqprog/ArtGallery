module web {

    requires domain.person;

    requires spring.web;
    requires spring.context;
    requires spring.webmvc;
    requires slf4j.api;
    requires spring.security.core;
    requires spring.security.web;
    requires lombok;
    requires spring.beans;
    requires spring.security.config;
    requires thymeleaf.layout.dialect;
    requires thymeleaf;
    requires thymeleaf.extras.springsecurity5;
    requires thymeleaf.spring5;
    requires javax.servlet.api;
    requires spring.data.commons;

}