package com.jarqprog.artGallery.domain.dto.thinDTO;

import com.jarqprog.artGallery.domain.dto.DomainDTO;
import com.jarqprog.artGallery.domain.dto.PictureDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class PictureThin extends DomainDTO implements PictureDTO, DTOThin {

    private String title;
    private String path;
    private long authorId;
    private long userId;
}
