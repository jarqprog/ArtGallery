package com.jarqprog.artGallery.api.domains.artistic.commentary;

import com.jarqprog.artGallery.api.domains.DomainEntity;
import com.jarqprog.artGallery.api.domains.artistic.picture.PictureEntity;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Table(name="commentaries")
public class CommentaryEntity extends DomainEntity {

    @NonNull
    @NotNull
    @Setter
    private String comment;

    @NonNull
    @NotNull
    @Setter
    private String userLogin;

    @NonNull
    @NotNull
    @ManyToOne
    @JoinColumn(name = "picture_id")
    private PictureEntity picture;

    CommentaryEntity(@NonNull @NotNull String comment, @NonNull @NotNull PictureEntity picture) {
        this.comment = comment;
        this.picture = picture;
    }

    CommentaryEntity(@NonNull @NotNull String comment, @NonNull @NotNull String userLogin,
                     @NonNull @NotNull PictureEntity picture) {
        this.comment = comment;
        this.userLogin = userLogin;
        this.picture = picture;
    }
}
