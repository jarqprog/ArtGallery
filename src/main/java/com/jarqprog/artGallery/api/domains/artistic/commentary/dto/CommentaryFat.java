package com.jarqprog.artGallery.api.domains.artistic.commentary.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import com.jarqprog.artGallery.api.domains.artistic.picture.model.PictureFat;
import com.jarqprog.artGallery.domain.artistic.Commentary;
import lombok.*;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class CommentaryFat extends DomainDTO implements Commentary, CommentaryDTO {

    private String comment;
    private String userLogin;
    private PictureFat picture;

    @Override
    public long getPictureId() {
        return getDTOId(picture);
    }

}
