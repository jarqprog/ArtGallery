package com.jarqprog.artGallery;

import com.jarqprog.artGallery.api.infrastructure.config.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.jarqprog.artGallery.api.domains"})
@Import(Config.class)
@ComponentScan("com.jarqprog.artGallery.api")
@ActiveProfiles("test")
public class SpringServiceTestConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
