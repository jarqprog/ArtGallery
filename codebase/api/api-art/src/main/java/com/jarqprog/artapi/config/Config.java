package com.jarqprog.artapi.config;

import com.jarqprog.artapi.database.ArtDatabaseConfig;
import com.jarqprog.commonapi.components.BCryptProvider;
import com.jarqprog.commonapi.components.DtoConverter;
import com.jarqprog.commonapi.components.DtoConverterImpl;
import com.jarqprog.commonapi.components.MapperProviderImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Optional;

import static com.jarqprog.artapi.config.ArtApiConstants.BASE_PACKAGE;
import static com.jarqprog.artapi.config.ArtApiConstants.BASE_PACKAGE_WILD_CARD;


@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = BASE_PACKAGE_WILD_CARD)
@ComponentScan(BASE_PACKAGE)
public class Config implements WebMvcConfigurer {

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    protected DataSource dataSource(@NonNull @Autowired ArtDatabaseConfig databaseConfig) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseConfig.getDriverClass());
        dataSource.setUrl(databaseConfig.getUrl());
        dataSource.setUsername(databaseConfig.getUser());
        dataSource.setPassword(databaseConfig.getPassword());
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@NonNull @Autowired DataSource dataSource,
                                                                       @NonNull @Autowired ArtDatabaseConfig databaseConfig) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(BASE_PACKAGE_WILD_CARD);
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
    public PasswordEncoder passwordEncoder() {
        return BCryptProvider.build().provide();
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
