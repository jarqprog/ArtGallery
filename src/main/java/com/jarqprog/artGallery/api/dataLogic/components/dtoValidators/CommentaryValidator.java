package com.jarqprog.artGallery.api.dataLogic.components.dtoValidators;

import com.jarqprog.artGallery.domain.dto.CommentaryDTO;

public interface CommentaryValidator {

    void validateOnCreation(CommentaryDTO commentaryDTO);
    void validateOnUpdate(CommentaryDTO commentaryDTO);

}
