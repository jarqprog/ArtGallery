package com.jarqprog.personapi.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Profile({DEV_PROFILE, TEST_PROFILE, HERO_PROFILE, PROD_PROFILE})
public class UserModuleConfig {

    @Bean
    PasswordValidator passwordValidator() {
        return new PasswordDummyValidator();
    }

}
