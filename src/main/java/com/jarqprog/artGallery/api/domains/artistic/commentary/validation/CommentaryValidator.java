package com.jarqprog.artGallery.api.domains.artistic.commentary.validation;

import com.jarqprog.artGallery.domain.artistic.Commentary;

public interface CommentaryValidator {

    void validateOnCreation(Commentary commentary);
    void validateOnUpdate(Commentary commentary);

}
