package com.jarqprog.artapi.domains.commentary.validation;

import com.jarqprog.artdomain.commentary.CommentaryData;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommentaryValidatorImpl implements CommentaryValidator {

    @Override
    public void validateOnCreation(@NonNull CommentaryData commentaryData) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateCommentIsNotEmpty(commentaryData, exceptionMessage);
        validatePictureId(commentaryData, exceptionMessage);
        validateUserLoginIsNotEmpty(commentaryData, exceptionMessage);
        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public void validateOnUpdate(@NonNull CommentaryData commentaryData) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateId(commentaryData, exceptionMessage);
        validateCommentIsNotEmpty(commentaryData, exceptionMessage);
        validatePictureId(commentaryData, exceptionMessage);
        validateUserLoginIsNotEmpty(commentaryData, exceptionMessage);
        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    private void throwExceptionIfMessageNotEmpty(StringBuilder exceptionMessage) {
        if (exceptionMessage.length() > 0) {
            exceptionMessage.insert(0, "Problem occurred on validation Commentary: ");
            throw new IllegalArgumentException(exceptionMessage.toString());
        }
    }

    private void validateCommentIsNotEmpty(CommentaryData commentaryData, StringBuilder stringBuilder) {
        if (StringUtils.isBlank(commentaryData.getComment())) {
            stringBuilder.append("Comment cannot be empty. ");
        }
    }

    private void validateUserLoginIsNotEmpty(CommentaryData commentaryData, StringBuilder stringBuilder) {
        if (StringUtils.isBlank(commentaryData.getUserLogin())) {
            stringBuilder.append("User login cannot be empty. ");
        }
    }

    private void validateId(CommentaryData commentaryData, StringBuilder stringBuilder) {
        if (commentaryData.getId() <= 0 ) {
            stringBuilder.append("ID is incorrect. ");
        }
    }

    private void validatePictureId(CommentaryData commentaryData, StringBuilder stringBuilder) {
        if (commentaryData.getPictureId() <= 0 ) {
            stringBuilder.append("PictureId is incorrect. ");
        }
    }
}
