package com.jarqprog.web;

import com.jarqprog.domainperson.model.contact.DomainContact;
import com.jarqprog.domainperson.model.user.DomainUser;
import com.jarqprog.personapi.domains.contact.ContactEntity;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.dialect.SpringStandardDialect;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebMvc
public class AppInitializer implements WebApplicationInitializer, WebMvcConfigurer, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(AppInitializer.class);

    private ApplicationContext applicationContext;

    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onStartup(ServletContext container) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.scan("com.jarqprog.web");
        rootContext.scan("com.jarqprog.web.*");
//        rootContext.register(SimpleSecurityConfig.class);
//        rootContext.register(ConfigWebModule.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = container
                .addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver bean = new InternalResourceViewResolver();
//
//        bean.setViewClass(JstlView.class);
//        bean.setPrefix("/WEB-INF/view/");
//        bean.setSuffix(".jsp");
//
//        return bean;
//    }

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
        logger.warn("***********************************************************************************************");
        logger.warn("***********************************************************************************************");
        logger.warn("AppInitializer");
        logger.warn("***********************************************************************************************");
        logger.warn("***********************************************************************************************");
        logger.warn(resolver.getName());
        logger.warn(resolver.getPrefix());

        return resolver;
    }

//    @Bean
//    protected DomainClassConverter<?> domainClassConverter() {
//        return new DomainClassConverter<>(mvcConversionService);
//    }

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
    protected Set<IDialect> thymeleafDialects() {
        Set<IDialect> dialects = new HashSet<>();
        dialects.add(new SpringStandardDialect());
        dialects.add(new LayoutDialect());
        dialects.add(new SpringSecurityDialect());
        return dialects;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    public static DomainUser getDummyUser() {
        final var mock = "mock";
        final var contact = ContactEntity.fromContact(DomainContact.createWith()
                .email("username@gmail.com")
                .firstName(mock)
                .build());
        final var user = DomainUser.createWith().login(mock).password(mock).contact(contact).build();
        logger.info("Dummy user created: {}", user);
        return user;
    }
}
