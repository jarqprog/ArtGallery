package com.jarqprog.artGallery.api.dataLogic.components.dtoValidators.implementations;

import com.jarqprog.artGallery.api.dataLogic.components.dtoValidators.PictureValidator;
import com.jarqprog.artGallery.domain.dto.PictureDTO;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PictureDTOValidator implements PictureValidator {

    @Override
    public void validateOnCreation(@NonNull PictureDTO pictureDTO) {
        StringBuilder exceptionMessage = new StringBuilder();
        validatePathIsNotEmpty(pictureDTO, exceptionMessage);

        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public void validateOnUpdate(@NonNull PictureDTO pictureDTO) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateId(pictureDTO, exceptionMessage);
        validatePathIsNotEmpty(pictureDTO, exceptionMessage);

        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public boolean isUserIdValid(PictureDTO pictureDTO) {
        return pictureDTO.getUserId() > 0;
    }

    @Override
    public boolean isAuthorIdValid(PictureDTO pictureDTO) {
        return pictureDTO.getAuthorId() > 0;
    }

    private void throwExceptionIfMessageNotEmpty(StringBuilder exceptionMessage) {
        if (exceptionMessage.length() > 0) {
            exceptionMessage.insert(0, "Problem occurred on validation Picture: ");
            throw new IllegalArgumentException(exceptionMessage.toString());
        }
    }

    private void validateId(PictureDTO pictureDTO, StringBuilder stringBuilder) {
        if (pictureDTO.getId() <= 0 ) {
            stringBuilder.append("ID is incorrect. ");
        }
    }

    private void validatePathIsNotEmpty(PictureDTO pictureDTO, StringBuilder stringBuilder) {
        if (StringUtils.isBlank(pictureDTO.getPath())) {
            stringBuilder.append("Path cannot be empty. ");
        }
    }
}
