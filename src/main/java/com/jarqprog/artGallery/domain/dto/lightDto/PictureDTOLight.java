package com.jarqprog.artGallery.domain.dto.lightDto;

import com.jarqprog.artGallery.domain.dto.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class PictureDTOLight extends DTO implements LightDTO {

    private String title;
    private String path;
    private long authorId;
    private long userId;
}
