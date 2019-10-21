package com.jarqprog.artGallery.springSecurity.config;


import com.jarqprog.artGallery.springWebMVC.handler.SimpleAccessDeniedHandler;
import com.jarqprog.artGallery.springWebMVC.handler.SimpleAuthenticationFailureHandler;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@ComponentScan("com.jarqprog.artGallery")
@EnableWebSecurity
public class SimpleSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public SimpleSecurityConfig() {
        super();
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(
                            "/",
                            "/api/**",
                            "/js/**",
                            "/css/**",
                            "/img/**",
                            "/webjars/**").permitAll()
                    .antMatchers("/user/registration", "/index", "/about-api").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
//                    .failureHandler(authenticationFailureHandler())
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    protected DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    protected AccessDeniedHandler accessDeniedHandler() {
        return new SimpleAccessDeniedHandler();
    }

    @Bean
    protected AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleAuthenticationFailureHandler();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
