package com.jarqprog.artGallery.springCommon;

import com.jarqprog.artGallery.springData.config.PersistenceConfig;
import com.jarqprog.artGallery.springSecurity.config.SimpleSecurityConfig;
import com.jarqprog.artGallery.springWebMVC.config.WebMvcConfig;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                PersistenceConfig.class, SimpleSecurityConfig.class, WebMvcConfig.class
        };
    }

    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    protected String[] getServletMappings() {
        return new String[] {
                "/"
        };
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new DelegatingFilterProxy("springSecurityFilterChain") };
    }
}

