package com.jarqprog.artGallery.api.domains.artistic.commentary.validation;

import com.jarqprog.artGallery.domain.artistic.CommentaryData;

public interface CommentaryValidator {

    void validateOnCreation(CommentaryData commentaryData);
    void validateOnUpdate(CommentaryData commentaryData);

}
