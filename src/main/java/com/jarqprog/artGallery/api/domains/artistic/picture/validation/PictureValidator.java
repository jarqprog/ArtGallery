package com.jarqprog.artGallery.api.domains.artistic.picture.validation;

import com.jarqprog.artGallery.domain.artistic.PictureData;

public interface PictureValidator {

    void validateOnCreation(PictureData pictureData);
    void validateOnUpdate(PictureData pictureData);
    boolean isAuthorIdValid(PictureData pictureData);

}
