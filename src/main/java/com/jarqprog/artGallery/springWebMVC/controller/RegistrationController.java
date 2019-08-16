package com.jarqprog.artGallery.springWebMVC.controller;

import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.domain.useCases.RegistrationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/user/registration")
public class RegistrationController {

    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public String showRegistrationForm(WebRequest request, Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("registration", registrationDTO);
        return "/user/registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("registration") @Valid RegistrationDTO registrationDTO,
                               BindingResult result, WebRequest request, Errors errors) {
        UserDTO userDTO = registrationService.registerUser(registrationDTO);
        return "/login";
    }


}
