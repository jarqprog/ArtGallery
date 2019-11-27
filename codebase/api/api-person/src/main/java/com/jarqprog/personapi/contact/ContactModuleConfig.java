package com.jarqprog.personapi.contact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactModuleConfig {

    @Bean
    ContactValidation contactValidation() {
        return new ContactValidationImpl(new EmailValidationPolicyImpl(), new NamesValidationPolicyImpl());
    }
}
