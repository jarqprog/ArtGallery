package com.jarqprog.artapi.domains.commentary;


import com.jarqprog.artapi.domains.picture.PictureEntity;
import com.jarqprog.artdomain.commentary.Commentary;
import com.jarqprog.artdomain.commentary.CommentaryData;
import com.jarqprog.artdomain.picture.Picture;
import com.jarqprog.commonapi.absmodel.DomainEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Access(AccessType.FIELD)
@Table(name="commentaries")
public class CommentaryEntity extends DomainEntity implements Commentary {


    public static CommentaryEntity fromCommentary(@NonNull final Commentary commentary) {
        assert commentary.getPicture() != null;
        return new CommentaryEntity(commentary);
    }

    @NonNull
    @NotNull
    private String comment;

    @NonNull
    @NotNull
    @Column(updatable = false)
    private String userLogin;

    @NonNull
    @NotNull
    @ManyToOne
    @JoinColumn(name = "picture_entity_id", updatable = false)
    private PictureEntity pictureEntity;


    private CommentaryEntity(@NonNull final Commentary commentary) {
        this(commentary.getId(),
            commentary.getVersion(),
            commentary.getComment(),
            commentary.getUserLogin(),
            PictureEntity.fromPicture(commentary.getPicture()));
    }

    private CommentaryEntity(long id, int version, @NonNull String comment, @NonNull String userLogin,
                             @NonNull final PictureEntity pictureEntity) {
        super(id, version);
        assert StringUtils.isNotBlank(comment);
        this.comment = comment;
        this.userLogin = StringUtils.isBlank(userLogin) ? CommentaryData.ANONYMOUS_USER : userLogin;
        this.pictureEntity = pictureEntity;
    }

    @Override
    @Transient
    public long getPictureId() {
        return getEntityId(pictureEntity);
    }

    @Override
    @Transient
    public Picture getPicture() {
        return pictureEntity;
    }
}
