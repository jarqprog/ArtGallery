package com.jarqprog.artGallery.web.controller;


import com.jarqprog.artGallery.domain.personal.ContactData;
import com.jarqprog.artGallery.api.domains.personal.user.dto.UserFat;
import com.jarqprog.artGallery.api.domains.personal.user.UserService;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserWebController {

    private static final Logger logger = LoggerFactory.getLogger(UserWebController.class);

    @NonNull private final UserService userService;

    @Autowired
    public UserWebController(@NonNull UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-info")
    public String showUserInfo(@NonNull Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        UserFat user = userService.findUserByLogin(login, UserFat.class);
        ContactData contactData = user.getContact();
        model.addAttribute("login", user.getLogin());
        model.addAttribute("email", contactData.getEmail());
        model.addAttribute("firstName", contactData.getFirstName());
        model.addAttribute("lastName", contactData.getLastName());
        return "/user/user-info";
    }
}
