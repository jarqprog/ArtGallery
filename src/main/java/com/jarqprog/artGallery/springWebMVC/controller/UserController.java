package com.jarqprog.artGallery.springWebMVC.controller;


import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.useCases.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-info")
    public String showUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        UserDTO userDTO = userService.findUserByLogin(login);
        ContactDTO contactDTO = userDTO.getContact();
        model.addAttribute("login", userDTO.getLogin());
        model.addAttribute("email", contactDTO.getEmail());
        model.addAttribute("firstName", contactDTO.getFirstName());
        model.addAttribute("lastName", contactDTO.getLastName());
        model.addAttribute("nickname", contactDTO.getNickname());
        return "/user/user-info";
    }
}
