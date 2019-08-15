package com.jarqprog.artGallery.springWebMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/api")
    public String userIndex() {
        return "api";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @GetMapping("/about-api")
    public String about() {
        return "about-api";
    }

    @GetMapping("/user-info")
    public String userInfo() {
        return "/user-info";
    }
}

