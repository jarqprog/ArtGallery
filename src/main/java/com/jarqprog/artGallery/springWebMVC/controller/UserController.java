package com.jarqprog.artGallery.springWebMVC.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/user-info")
    public String userInfo() {
        return "/user/user-info";
    }

}
