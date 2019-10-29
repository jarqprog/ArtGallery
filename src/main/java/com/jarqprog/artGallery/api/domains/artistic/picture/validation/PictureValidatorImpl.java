package com.jarqprog.artGallery.api.domains.artistic.picture.validation;

import com.jarqprog.artGallery.domain.artistic.Picture;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PictureValidatorImpl implements PictureValidator {

    @Override
    public void validateOnCreation(@NonNull Picture picture) {
        StringBuilder exceptionMessage = new StringBuilder();
        validatePathIsNotEmpty(picture, exceptionMessage);

        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public void validateOnUpdate(@NonNull Picture picture) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateId(picture, exceptionMessage);
        validatePathIsNotEmpty(picture, exceptionMessage);

        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public boolean isAuthorIdValid(Picture picture) {
        return picture.getAuthorId() > 0;
    }

    private void throwExceptionIfMessageNotEmpty(StringBuilder exceptionMessage) {
        if (exceptionMessage.length() > 0) {
            exceptionMessage.insert(0, "Problem occurred on validation Picture: ");
            throw new IllegalArgumentException(exceptionMessage.toString());
        }
    }

    private void validateId(Picture picture, StringBuilder stringBuilder) {
        if (picture.getId() <= 0 ) {
            stringBuilder.append("ID is incorrect. ");
        }
    }

    private void validatePathIsNotEmpty(Picture picture, StringBuilder stringBuilder) {
        if (StringUtils.isBlank(picture.getPath())) {
            stringBuilder.append("Path cannot be empty. ");
        }
    }
}
