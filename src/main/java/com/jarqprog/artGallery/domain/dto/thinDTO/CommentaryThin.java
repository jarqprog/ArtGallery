package com.jarqprog.artGallery.domain.dto.thinDTO;

import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.dto.DomainDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CommentaryThin extends DomainDTO implements CommentaryDTO, DTOThin {

    private String comment;
    private long userId;
    private long pictureId;

}
