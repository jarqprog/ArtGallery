package com.jarqprog.artapi.domains.picture.validation;


import com.jarqprog.artdomain.picture.PictureData;

public interface PictureValidator {

    void validateOnCreation(PictureData pictureData);
    void validateOnUpdate(PictureData pictureData);
    boolean isAuthorIdValid(PictureData pictureData);

}
