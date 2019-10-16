package com.jarqprog.artGallery.springData.components;

import com.jarqprog.artGallery.springData.dataLoader.InitialDataLoader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile({"test", "dev"})
//@Profile({"dev", "prod"})
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = Logger.getLogger(MyApplicationListener.class);

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
