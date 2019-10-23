package com.jarqprog.artGallery.springData.components.dtoValidators.implementations;

import com.jarqprog.artGallery.domain.dto.lightDto.CommentaryDTOLight;
import com.jarqprog.artGallery.springData.components.dtoValidators.CommentaryValidator;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommentaryDTOValidator implements CommentaryValidator {

    @Override
    public void validateOnCreation(@NonNull CommentaryDTOLight commentaryDTO) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateCommentIsNotEmpty(commentaryDTO, exceptionMessage);
        validatePictureId(commentaryDTO, exceptionMessage);
        validateUserId(commentaryDTO, exceptionMessage);

        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public void validateOnUpdate(@NonNull CommentaryDTOLight commentaryDTO) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateId(commentaryDTO, exceptionMessage);
        validateCommentIsNotEmpty(commentaryDTO, exceptionMessage);
        validatePictureId(commentaryDTO, exceptionMessage);
        validateUserId(commentaryDTO, exceptionMessage);

        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    private void throwExceptionIfMessageNotEmpty(StringBuilder exceptionMessage) {
        if (exceptionMessage.length() > 0) {
            exceptionMessage.insert(0, "Problem occurred on validation Commentary: ");
            throw new IllegalArgumentException(exceptionMessage.toString());
        }
    }

    private void validateCommentIsNotEmpty(CommentaryDTOLight commentaryDTO, StringBuilder stringBuilder) {
        if (StringUtils.isBlank(commentaryDTO.getComment())) {
            stringBuilder.append("Comment cannot be empty. ");
        }
    }

    private void validateId(CommentaryDTOLight commentaryDTO, StringBuilder stringBuilder) {
        if (commentaryDTO.getId() <= 0 ) {
            stringBuilder.append("ID is incorrect. ");
        }
    }

    private void validatePictureId(CommentaryDTOLight commentaryDTO, StringBuilder stringBuilder) {
        if (commentaryDTO.getPictureId() <= 0 ) {
            stringBuilder.append("PictureId is incorrect. ");
        }
    }

    private void validateUserId(CommentaryDTOLight commentaryDTO, StringBuilder stringBuilder) {
        if (commentaryDTO.getUserId() <= 0 ) {
            stringBuilder.append("UserId is incorrect. ");
        }
    }
}
