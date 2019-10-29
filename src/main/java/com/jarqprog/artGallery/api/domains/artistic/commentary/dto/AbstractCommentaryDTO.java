package com.jarqprog.artGallery.api.domains.artistic.commentary.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper=true)
abstract class AbstractCommentaryDTO extends DomainDTO implements CommentaryDTO {

    private String comment;

    private String userLogin;

    public AbstractCommentaryDTO(@NonNull String comment) {
        this.comment = comment;
    }

    public AbstractCommentaryDTO(@NonNull String comment, @NonNull String userLogin) {
        this.comment = comment;
        this.userLogin = userLogin;
    }
}
