package com.jarqprog.artapi.domains.picture;


import com.jarqprog.artapi.domains.author.AuthorEntity;
import com.jarqprog.artdomain.author.Author;
import com.jarqprog.artdomain.picture.Picture;
import com.jarqprog.artdomain.picture.PictureData;
import com.jarqprog.commonapi.absmodel.DomainEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Access(AccessType.FIELD)
@Table(name="pictures")
public class PictureEntity extends DomainEntity implements Picture {


    public static PictureEntity fromPicture(@NonNull final Picture picture) {
        return new PictureEntity(picture);
    }

    private String title;
    private String path;

    @Column(updatable = false)
    private String userLogin;

    @ManyToOne
    @JoinColumn(name = "author_entity_id")
    @Getter(AccessLevel.PACKAGE)
    private AuthorEntity authorEntity;


    private PictureEntity(@NonNull final Picture picture) {
        this(picture.getId(),
                picture.getVersion(),
                picture.getTitle(),
                picture.getPath(),
                picture.getUserLogin(),
                picture.hasAuthor() ? AuthorEntity.fromAuthor(picture.getAuthor()) : null);
    }

    private PictureEntity(long id, int version, @NonNull String title, @NonNull String path,
                          String userLogin, AuthorEntity author) {
        super(id, version);
        this.title = StringUtils.isBlank(title) ? PictureData.UNTITLED : title;
        assert StringUtils.isNotBlank(path);
        this.path = path;
        this.userLogin = StringUtils.isBlank(userLogin) ? PictureData.ANONYMOUS_USER : userLogin;
        this.authorEntity = author;
    }

    @Override
    @Transient
    public Author getAuthor() {
        return getAuthorEntity();
    }

    @Override
    @Transient
    public long getAuthorId() {
        return getEntityId(authorEntity);
    }

    @Override
    @Transient
    public boolean hasAuthor() {
        return authorEntity != null;
    }
}
