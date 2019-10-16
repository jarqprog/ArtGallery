package com.jarqprog.artGallery;

import com.jarqprog.artGallery.springData.config.PersistenceConfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.jarqprog.artGallery.springData.repository"})
@Import(PersistenceConfig.class)
@ComponentScan({"com.jarqprog.artGallery.springData.services",
                "com.jarqprog.artGallery.springData.dataLoader.impl"})
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
