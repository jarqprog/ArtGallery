package com.jarqprog.web.security.config;


import com.jarqprog.web.handler.SimpleAccessDeniedHandler;
import com.jarqprog.web.handler.SimpleAuthenticationFailureHandler;
import com.jarqprog.web.security.userDetails.SimpleUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ComponentScan(basePackageClasses = {SimpleUserDetailsService.class})
@EnableWebSecurity
public class SimpleSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SimpleSecurityConfig.class);

    static {


        logger.warn("***********************************************************************************************");
        logger.warn("***********************************************************************************************");
        logger.warn("SimpleSecurityConfig CREATED");
        logger.warn("***********************************************************************************************");
        logger.warn("***********************************************************************************************");

    }

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
//                .csrf().ignoringAntMatchers("/api","/api/**")// todo temp
//                    .and()
                .authorizeRequests()
                    .antMatchers(
                            "/",
//                            "/api/**",
                            "/js/**",
                            "/css/**",
                            "/img/**",
                            "/webjars/**").permitAll()
                    .antMatchers("/", "/user/registration", "/index", "/about-api").permitAll()
                    .anyRequest().permitAll(); //.authenticated()
//                    .and()
//                .formLogin()
////                    .failureHandler(authenticationFailureHandler())
//                    .loginPage("/login")
//                    .permitAll()
//                    .and()
//                .logout()
//                    .invalidateHttpSession(true)
//                    .clearAuthentication(true)
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/login?logout")
//                    .permitAll()
//                    .and()
//                .exceptionHandling()
//                    .accessDeniedHandler(accessDeniedHandler());
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
