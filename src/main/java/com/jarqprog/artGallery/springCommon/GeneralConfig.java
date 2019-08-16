package com.jarqprog.artGallery.springCommon;

import com.jarqprog.artGallery.domain.helper.*;
import com.jarqprog.artGallery.domain.helper.implementation.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.jarqprog.artGallery")
public class GeneralConfig {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean //todo only for DEV
    public PasswordValidator passwordValidator() {
        return new DummyPasswordValidator();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DtoEntityConverter dtoEntityConverter() {
        return new DtoEntityConverterImpl();
    }

    @Bean
    public ContactCreator contactCreator() {
        return new ContactCreatorImpl();
    }

    @Bean
    public UserCreator userCreator() {
        return new UserCreatorImpl();
    }

    @Bean
    public RegistrationValidator registrationValidator() {
        return new RegistrationValidatorImpl();
    }

}
