package com.jarqprog.web.controller;


import com.jarqprog.domainperson.model.user.UserData;
import com.jarqprog.web.service.registration.RegistrationFormDTO;
import com.jarqprog.web.service.registration.RegistrationService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(@NonNull RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String showRegistrationForm(WebRequest request, Model model) {
        RegistrationFormDTO registrationFormDTO = new RegistrationFormDTO();
        model.addAttribute("registration", registrationFormDTO);
        return "user/registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("registration") @Valid RegistrationFormDTO registrationFormDTO,
                               BindingResult result, WebRequest request, Errors errors, Model model) {
        UserData userDTO = registrationService.registerUser(registrationFormDTO);
        logger.info("User {} registration", userDTO);
        model.addAttribute("login", userDTO.getLogin());
        return "user/registration-success";
    }
}
