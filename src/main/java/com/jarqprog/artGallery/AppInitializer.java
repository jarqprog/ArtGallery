package com.jarqprog.artGallery;

import com.jarqprog.artGallery.config.repository.RepositoryConfig;
import com.jarqprog.artGallery.config.security.SimpleSecurityConfig;
import com.jarqprog.artGallery.springWebMVC.WebMvcConfig;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                RepositoryConfig.class, SimpleSecurityConfig.class, WebMvcConfig.class
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

