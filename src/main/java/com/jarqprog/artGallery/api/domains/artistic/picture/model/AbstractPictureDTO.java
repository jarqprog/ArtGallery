package com.jarqprog.artGallery.api.domains.artistic.picture.model;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
abstract class AbstractPictureDTO extends DomainDTO implements PictureDTO {

    private String title;

    private String path;

    private String userLogin;

    public AbstractPictureDTO(@NonNull String title) {
        this.title = title;
    }

    public AbstractPictureDTO(@NonNull String title, @NonNull String userLogin) {
        this.title = title;
        this.userLogin = userLogin;
    }
}
