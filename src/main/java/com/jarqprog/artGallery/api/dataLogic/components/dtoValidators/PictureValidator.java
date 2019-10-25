package com.jarqprog.artGallery.api.dataLogic.components.dtoValidators;

import com.jarqprog.artGallery.domain.dto.PictureDTO;

public interface PictureValidator {

    void validateOnCreation(PictureDTO pictureDTO);
    void validateOnUpdate(PictureDTO pictureDTO);
    boolean isUserIdValid(PictureDTO pictureDTO);
    boolean isAuthorIdValid(PictureDTO pictureDTO);

}
