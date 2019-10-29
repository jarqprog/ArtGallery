package com.jarqprog.artGallery.api.domains.artistic.commentary.dto;

import com.jarqprog.artGallery.api.domains.DTOThin;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=true)
public class CommentaryThin extends AbstractCommentaryDTO implements DTOThin {

    private long pictureId;

    public CommentaryThin(@NonNull String comment, long pictureId) {
        super(comment);
        this.pictureId = pictureId;
    }

    public CommentaryThin(@NonNull String comment, @NonNull String userLogin, long pictureId) {
        super(comment, userLogin);
        this.pictureId = pictureId;
    }
}
