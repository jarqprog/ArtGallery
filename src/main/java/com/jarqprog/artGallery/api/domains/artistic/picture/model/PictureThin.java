package com.jarqprog.artGallery.api.domains.artistic.picture.model;

import com.jarqprog.artGallery.api.domains.DTOThin;;
import lombok.*;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=true)
public class PictureThin extends AbstractPictureDTO implements DTOThin {

    private long authorId;

    public PictureThin(@NonNull String title) {
        super(title);
    }

    public PictureThin(@NonNull String title, @NonNull String userLogin) {
        super(title, userLogin);
    }
}
