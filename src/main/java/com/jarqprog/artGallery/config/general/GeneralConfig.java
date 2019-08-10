package com.jarqprog.artGallery.config.general;


import com.jarqprog.artGallery.config.AppConfiguration;
import com.jarqprog.artGallery.config.GeneralConfiguration;
import com.jarqprog.artGallery.config.data.database.DatabaseConfig;
import com.jarqprog.artGallery.config.data.database.MySQLConfig;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.helper.SimpleDtoEntityConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.jarqprog.artGallery")
public class RootConfig {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DtoEntityConverter dtoEntityConverter() {
        return new SimpleDtoEntityConverter();
    }

    @Bean
    public AppConfiguration appConfiguration() {
        return new GeneralConfiguration();
    }

    @Bean
    public DatabaseConfig databaseConfig() {
        return new MySQLConfig();
    }
}
