package com.jarqprog.web;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import static com.jarqprog.web.config.WebAppConstants.BASE_PACKAGE;

public class WebApplication implements WebApplicationInitializer {

    private static final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

    public static AnnotationConfigWebApplicationContext getApplicationContext() {
        return rootContext;
    }

    @Override
    public void onStartup(ServletContext container) {
        rootContext.scan(BASE_PACKAGE);
        container.addListener(new ContextLoaderListener(rootContext));
        ServletRegistration.Dynamic dispatcher = container
                .addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
