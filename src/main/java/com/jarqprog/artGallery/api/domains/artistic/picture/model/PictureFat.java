package com.jarqprog.artGallery.api.domains.artistic.picture.model;

import com.jarqprog.artGallery.api.domains.DTOFat;
import com.jarqprog.artGallery.api.domains.artistic.author.dto.AuthorFat;
import lombok.*;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=true)
public class PictureFat extends AbstractPictureDTO implements DTOFat {

    private AuthorFat author;

    public PictureFat(@NonNull String title) {
        super(title);
    }

    public PictureFat(@NonNull String title, @NonNull String userLogin) {
        super(title, userLogin);
    }

    @Override
    public long getAuthorId() {
        return getDTOId(author);
    }
}
