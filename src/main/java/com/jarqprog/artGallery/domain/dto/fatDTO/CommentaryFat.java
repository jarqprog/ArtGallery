package com.jarqprog.artGallery.domain.dto.fatDTO;

import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.dto.DomainDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CommentaryFat extends DomainDTO implements CommentaryDTO, DTOFat {

    private String comment;
    private UserFat user;
    private PictureFat picture;;

    @Override
    public long getUserId() {
        return user != null ? user.getId() : 0;
    }

    @Override
    public long getPictureId() {
        return picture != null ? picture.getId() : 0;
    }

}
