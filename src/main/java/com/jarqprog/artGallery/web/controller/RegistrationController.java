package com.jarqprog.artGallery.web.controller;

import com.jarqprog.artGallery.domain.dto.heavyDto.UserDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.api.dataLogic.useCases.RegistrationService;

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
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("registration", registrationDTO);
        return "user/registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("registration") @Valid RegistrationDTO registrationDTO,
                               BindingResult result, WebRequest request, Errors errors, Model model) {
        UserDTO userDTO = registrationService.registerUser(registrationDTO);
        model.addAttribute("login", userDTO.getLogin());
        return "user/registration-success";
    }
}
