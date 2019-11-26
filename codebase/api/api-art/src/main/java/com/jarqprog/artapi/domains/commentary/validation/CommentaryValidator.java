package com.jarqprog.artapi.domains.commentary.validation;


import com.jarqprog.artdomain.commentary.CommentaryData;

public interface CommentaryValidator {

    void validateOnCreation(CommentaryData commentaryData);
    void validateOnUpdate(CommentaryData commentaryData);

}
