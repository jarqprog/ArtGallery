package com.jarqprog.artdomain.picture;


import com.jarqprog.commondomain.absmodel.DomainModel;
import com.jarqprog.artdomain.author.Author;
import lombok.*;
import org.apache.commons.lang3.StringUtils;


@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class DomainPicture extends DomainModel implements Picture {


    public static DomainPicture mergeID(long id, @NonNull final Picture picture) {
        assert id > 0;
        return new DomainPicture(id, picture);
    }

    public static DomainPicture fromPicture(@NonNull final Picture picture) {
        return new DomainPicture(picture);
    }

    @Builder(builderMethodName = "createWith")
    public static DomainPicture buildWithData(long id, int version, @NonNull String title, @NonNull String path,
                                              String userLogin, Author author) {
        return new DomainPicture(id, version, title, path, userLogin, author);
    }

    @NonNull
    private final String title;

    @NonNull
    private final String path;

    @NonNull
    private final String userLogin;

    private final Author author;

    @Override
    public long getAuthorId() {
        return getModelId(author);
    }

    @Override
    public boolean hasAuthor() {
        return author != null;
    }


    private DomainPicture(long id, @NonNull final Picture picture) {
        this(id,
            picture.getVersion(),
            picture.getTitle(),
            picture.getPath(),
            picture.getUserLogin(),
            picture.getAuthor());
    }

    private DomainPicture(@NonNull final Picture picture) {
        this(picture.getId(), picture);
    }

    private DomainPicture(long id, int version, @NonNull String title, @NonNull String path,
                          String userLogin, Author author) {
        super(id, version);
        this.title = StringUtils.isBlank(title) ? UNTITLED : title;
        assert StringUtils.isNotBlank(path);
        this.path = path;
        this.userLogin = StringUtils.isBlank(userLogin) ? ANONYMOUS_USER : userLogin;
        this.author = author;
    }
}