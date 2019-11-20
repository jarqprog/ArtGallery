package com.jarqprog.personapi;

import com.jarqprog.commonapi.DatabaseConfig;
import com.jarqprog.commonapi.components.DtoConverter;
import com.jarqprog.commonapi.components.DtoConverterImpl;
import com.jarqprog.commonapi.components.MapperProviderImpl;
import com.jarqprog.commonapi.constants.ApiConstants;
import com.jarqprog.personapi.database.H2DevConfig;
import com.jarqprog.personapi.database.H2TestConfig;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Optional;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class PersonApiModuleConfig {

    @Bean
    protected DataSource personDataSource(@Autowired @NonNull DatabaseConfig databaseConfig) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseConfig.getDriverClass());
        dataSource.setUrl(databaseConfig.getUrl());
        dataSource.setUsername(databaseConfig.getUser());
        dataSource.setPassword(databaseConfig.getPassword());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager personTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean personEntityManagerFactory(@Autowired @NonNull DatabaseConfig databaseConfig) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(personDataSource(databaseConfig));
        em.setPackagesToScan("com.jarqprog.personapi");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(databaseConfig.getJPAProperties());
        return em;
    }

    @Bean
    public DtoConverter dtoConverter() {
        final var modelMapper = MapperProviderImpl.provide();
        return DtoConverterImpl.builder()
                .modelMapper(modelMapper)
                .build();
    }

    @Bean
    @Profile(ApiConstants.DEV_PROFILE)
    public DatabaseConfig devDatabase() {
        return H2DevConfig.build();
    }

    @Bean
    @Profile(ApiConstants.TEST_PROFILE)
    public DatabaseConfig testDatabase() {
        return H2TestConfig.build();
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        //todo to check
        /*
          if you are using spring springSecurity, you can get the currently logged username with following code segment.
          SecurityContextHolder.getContext().getAuthentication().getName()
         */
        return () -> Optional.ofNullable("auditorProvider");
    }

}
