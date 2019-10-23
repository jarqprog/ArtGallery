package com.jarqprog.artGallery.domain.dto.lightDto;

import com.jarqprog.artGallery.domain.dto.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CommentaryDTOLight extends DTO implements LightDTO {

    private String comment;
    private long userId;
    private long pictureId;

}
