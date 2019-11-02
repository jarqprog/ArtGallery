package com.jarqprog.artGallery.api.infrastructure.components.dataLoader;

import com.jarqprog.artGallery.api.ApiConstants;
import org.springframework.context.annotation.Profile;

@Profile({ApiConstants.TEST_PROFILE, ApiConstants.DEV_PROFILE})
public interface InitialDataLoader {

    String FAKE_PICTURE_PATH = "fake/path";
    String BETTY_LOGIN = "betty80";
    String BETTY_PASSWORD = "1%BettyBetty";
    String BETTY_NAME = "Betty";
    String BETTY_LAST_NAME = "Sue";
    String BETTY_ARTIST_NICK = "betty-artist";
    String BETTY_MAIL = "bettys@gmail.com";
    String BETTY_FIRST_PICTURE_TITLE = "Spring";
    String BETTY_SECOND_PICTURE_TITLE = "Summer";
    String BETTY_FIRST_COMMENT = "This is my first painting";
    String BETTY_SECOND_COMMENT = "Do you like it?";
    int FIRST_PICTURE_COMMENTARIES_QUANTITY = 3;


    void populateDb();
}
