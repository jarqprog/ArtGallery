package com.jarqprog.artGallery.springWebMVC.controller;


import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.UserRegistrationDTOImpl;
import com.jarqprog.artGallery.domain.interfaces.UserRegistrationDTO;
import com.jarqprog.artGallery.domain.useCases.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    private static final Logger logger = Logger.getLogger(UserRegistrationController.class);

    @Autowired UserService userService;

    @GetMapping("/user/user-info")
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


    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTOImpl();
        model.addAttribute("userRegistration", userRegistrationDTO);
        return "/user/registration";
    }

    @PostMapping("/user/registration")
    public String registerUser(@ModelAttribute("userRegistration") @Valid UserRegistrationDTO userRegistrationDTO,
                                     BindingResult result, WebRequest request, Errors errors) {

        logger.info(result);
        logger.info(userRegistrationDTO);
        return "/login";
    }


}
