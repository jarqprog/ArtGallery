package com.jarqprog.personapi.seederdb;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import static com.jarqprog.commonapi.constants.ApiConstants.*;

@Component
@Profile({DEV_PROFILE, TEST_PROFILE, HERO_PROFILE})
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(AppListener.class);

    private final SeedingPersonDomain seedingPersonDomain;

    private boolean alreadySetup = false;

    @Autowired
    public AppListener(@NonNull SeedingPersonDomain seedingPersonDomain) {
        this.seedingPersonDomain = seedingPersonDomain;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }
        logger.info("********************************************");
        logger.info("#### Populating database");
        seedingPersonDomain.populateDb();
        logger.info("#### Database prepared");
        logger.info("********************************************");
        alreadySetup = true;
    }
}
