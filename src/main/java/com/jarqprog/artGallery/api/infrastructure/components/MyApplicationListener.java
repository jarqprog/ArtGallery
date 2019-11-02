package com.jarqprog.artGallery.api.infrastructure.components;

import com.jarqprog.artGallery.api.ApiConstants;
import com.jarqprog.artGallery.api.infrastructure.components.dataLoader.InitialDataLoader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile({ApiConstants.DEV_PROFILE, ApiConstants.TEST_PROFILE})
//@Profile({"dev", "prod"})
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

    private final InitialDataLoader initialDataLoader;

    private boolean alreadySetup = false;

    @Autowired
    public MyApplicationListener(InitialDataLoader initialDataLoader) {
        this.initialDataLoader = initialDataLoader;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }
        logger.info("********************************************");
        logger.info("#### MyApplicationListener: Populating database");
        initialDataLoader.populateDb();
        logger.info("#### MyApplicationListener: Database prepared");
        logger.info("********************************************");
        alreadySetup = true;
    }
}
