package com.jarqprog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    static {


        logger.warn("***********************************************************************************************");
        logger.warn("***********************************************************************************************");
        logger.warn("CONTROLLER CREATED");
        logger.warn("***********************************************************************************************");
        logger.warn("***********************************************************************************************");

    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/index1")
    public String root1() {
        return "index1";
    }

    @GetMapping("/api")
    public String userIndex() {
        return "api";
    }

    @GetMapping("/login")
    public String login() {
        return "index1";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @GetMapping("/error")
    public String error() {
        return "/error/error";
    }

    @GetMapping("/about-api")
    public String about() {
        return "about-api";
    }

}

