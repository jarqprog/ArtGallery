package com.jarqprog.artGallery.api.domains.artistic.commentary.validation;

import com.jarqprog.artGallery.domain.artistic.Commentary;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommentaryValidatorImpl implements CommentaryValidator {

    @Override
    public void validateOnCreation(@NonNull Commentary commentary) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateCommentIsNotEmpty(commentary, exceptionMessage);
        validatePictureId(commentary, exceptionMessage);
        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public void validateOnUpdate(@NonNull Commentary commentary) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateId(commentary, exceptionMessage);
        validateCommentIsNotEmpty(commentary, exceptionMessage);
        validatePictureId(commentary, exceptionMessage);
        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    private void throwExceptionIfMessageNotEmpty(StringBuilder exceptionMessage) {
        if (exceptionMessage.length() > 0) {
            exceptionMessage.insert(0, "Problem occurred on validation Commentary: ");
            throw new IllegalArgumentException(exceptionMessage.toString());
        }
    }

    private void validateCommentIsNotEmpty(Commentary commentary, StringBuilder stringBuilder) {
        if (StringUtils.isBlank(commentary.getComment())) {
            stringBuilder.append("Comment cannot be empty. ");
        }
    }

    private void validateId(Commentary commentary, StringBuilder stringBuilder) {
        if (commentary.getId() <= 0 ) {
            stringBuilder.append("ID is incorrect. ");
        }
    }

    private void validatePictureId(Commentary commentary, StringBuilder stringBuilder) {
        if (commentary.getPictureId() <= 0 ) {
            stringBuilder.append("PictureId is incorrect. ");
        }
    }
}
