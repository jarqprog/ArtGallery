package com.jarqprog.artGallery.api.domains.artistic.commentary.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import lombok.*;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class CommentaryThin extends DomainDTO implements CommentaryDTO {

    private String comment;
    private String userLogin;
    private long pictureId;

}
