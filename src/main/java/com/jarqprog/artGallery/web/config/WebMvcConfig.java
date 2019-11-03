package com.jarqprog.artGallery.web.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.dialect.SpringStandardDialect;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan("com.jarqprog.artGallery")
public class WebMvcConfig implements ApplicationContextAware, WebMvcConfigurer {

    private ApplicationContext applicationContext;
    private final FormattingConversionService mvcConversionService;

    @Autowired
    public WebMvcConfig(ApplicationContext applicationContext, FormattingConversionService mvcConversionService) {
        this.applicationContext = applicationContext;
        this.mvcConversionService = mvcConversionService;
    }

    @Autowired
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    protected ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Bean
    protected SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(templateResolver());
        engine.setDialects(thymeleafDialects());
        return engine;
    }

    private ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("classpath:/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/WEB-INF/static/img/",
                        "classpath:/WEB-INF/static/css/",
                        "classpath:/WEB-INF/static/js/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    protected DomainClassConverter<?> domainClassConverter() {
        return new DomainClassConverter<>(mvcConversionService);
    }

    @Bean
    protected Set<IDialect> thymeleafDialects() {
        Set<IDialect> dialects = new HashSet<>();
        dialects.add(new SpringStandardDialect());
        dialects.add(new LayoutDialect());
        dialects.add(new SpringSecurityDialect());
        return dialects;
    }
}