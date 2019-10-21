package com.jarqprog.artGallery.springCommon;

import com.jarqprog.artGallery.springData.config.PersistenceConfig;
import com.jarqprog.artGallery.springSecurity.config.SimpleSecurityConfig;
import com.jarqprog.artGallery.springWebMVC.config.WebMvcConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@Configuration
@EnableWebMvc
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(GeneralConfig.class);
        rootContext.register(PersistenceConfig.class);
        rootContext.register(SimpleSecurityConfig.class);
        rootContext.register(WebMvcConfig.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = container
                .addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}

