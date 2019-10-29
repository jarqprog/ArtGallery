package com.jarqprog.artGallery.api.domains.artistic.picture.validation;

import com.jarqprog.artGallery.domain.artistic.Picture;

public interface PictureValidator {

    void validateOnCreation(Picture picture);
    void validateOnUpdate(Picture picture);
    boolean isAuthorIdValid(Picture picture);

}
