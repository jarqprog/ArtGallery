package com.jarqprog.artGallery.config.security;


import com.jarqprog.artGallery.service.user.SimpleUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;



@Configuration
@ComponentScan("com.jarqprog.artGallery")
@EnableWebSecurity
public class SimpleSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired private SimpleUserDetailsService userDetailsService;

    @Autowired private AccessDeniedHandler accessDeniedHandler;

    @Autowired private AuthenticationFailureHandler authenticationFailureHandler;



    public SimpleSecurityConfig() {
        super();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new SimpleUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/index").permitAll()
                .antMatchers("/api/contacts/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/api/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .formLogin()
                .successForwardUrl("/index.html")
                .permitAll()
//                .loginPage("/login.html")
//                .usernameParameter("username")
//                .passwordParameter("password")
                .failureHandler(authenticationFailureHandler)
                .successForwardUrl("/index.html")
                .and()
                .logout()
                .logoutSuccessUrl("/index.html");
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new SimpleAccessDeniedHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleAuthenticationFailureHandler();
    }
}
