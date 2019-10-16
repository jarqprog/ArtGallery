package com.jarqprog.artGallery.domain.useCases;

import com.jarqprog.artGallery.SpringServiceTestConfig;
import com.jarqprog.artGallery.springData.dataLoader.InitialDataLoader;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
public abstract class ServiceTestWithH2DB {

    private static final Logger logger = Logger.getLogger(ServiceTestWithH2DB.class);
    private static boolean isPopulated = false;

    @Autowired
    private InitialDataLoader initialDataLoader;

    @BeforeEach
    public void initData() {
        assert initialDataLoader != null;
        if (!isPopulated) {
            logger.info("********************************************");
            logger.info("#### Populating test database");
            initialDataLoader.populateDb();
            logger.info("#### Database prepared");
            logger.info("********************************************");
            isPopulated = true;
        }
    }
}
