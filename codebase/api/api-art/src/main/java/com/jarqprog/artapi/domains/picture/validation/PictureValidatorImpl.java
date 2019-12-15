package com.jarqprog.artapi.domains.picture.validation;

import com.jarqprog.artdomain.picture.PictureData;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PictureValidatorImpl implements PictureValidator {

    @Override
    public void validateOnCreation(@NonNull PictureData pictureData) {
        StringBuilder exceptionMessage = new StringBuilder();
        validatePathIsNotEmpty(pictureData, exceptionMessage);

        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public void validateOnUpdate(@NonNull PictureData pictureData) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateId(pictureData, exceptionMessage);
        validatePathIsNotEmpty(pictureData, exceptionMessage);

        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public boolean isAuthorIdValid(PictureData pictureData) {
        return pictureData.getAuthorId() > 0;
    }

    private void throwExceptionIfMessageNotEmpty(StringBuilder exceptionMessage) {
        if (exceptionMessage.length() > 0) {
            exceptionMessage.insert(0, "Problem occurred on validation Picture: ");
            throw new IllegalArgumentException(exceptionMessage.toString());
        }
    }

    private void validateId(PictureData pictureData, StringBuilder stringBuilder) {
        if (pictureData.getId() <= 0 ) {
            stringBuilder.append("ID is incorrect. ");
        }
    }

    private void validatePathIsNotEmpty(PictureData pictureData, StringBuilder stringBuilder) {
        if (StringUtils.isBlank(pictureData.getPath())) {
            stringBuilder.append("Path cannot be empty. ");
        }
    }
}
