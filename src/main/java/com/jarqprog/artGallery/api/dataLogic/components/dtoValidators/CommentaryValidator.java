package com.jarqprog.artGallery.api.dataLogic.components.dtoValidators;

import com.jarqprog.artGallery.domain.dto.lightDto.CommentaryDTOLight;

public interface CommentaryValidator {

    void validateOnCreation(CommentaryDTOLight commentaryDTO);
    void validateOnUpdate(CommentaryDTOLight commentaryDTO);

}
