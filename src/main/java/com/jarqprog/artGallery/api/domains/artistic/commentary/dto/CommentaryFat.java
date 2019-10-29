package com.jarqprog.artGallery.api.domains.artistic.commentary.dto;

import com.jarqprog.artGallery.api.domains.DTOFat;
import com.jarqprog.artGallery.api.domains.artistic.picture.model.PictureFat;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=true)
public class CommentaryFat extends AbstractCommentaryDTO implements DTOFat {

    private PictureFat picture;

    public CommentaryFat(@NonNull String comment, @NonNull PictureFat picture) {
        super(comment);
        this.picture = picture;
    }

    public CommentaryFat(@NonNull String comment, @NonNull String userLogin, @NonNull PictureFat picture) {
        super(comment, userLogin);
        this.picture = picture;
    }

    @Override
    public long getPictureId() {
        return getDTOId(picture);
    }

}
