package com.jarqprog.artdomain.commentary;

import com.jarqprog.commondomain.IdentityModel;
import com.jarqprog.artdomain.picture.Picture;
import lombok.*;
import org.apache.commons.lang3.StringUtils;


@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DomainCommentary extends IdentityModel implements Commentary {

    public static DomainCommentary mergeID(long id, @NonNull final Commentary commentary) {
        return new DomainCommentary(id, commentary);
    }

    public static DomainCommentary fromCommentary(@NonNull final Commentary commentary) {
        return new DomainCommentary(commentary);
    }

    @Builder(builderMethodName = "createWith")
    public static DomainCommentary buildWithData(long id, int version,
                                                 @NonNull String comment,
                                                 @NonNull String userLogin,
                                                 @NonNull final Picture picture) {
        return new DomainCommentary(id, version, comment, userLogin, picture);
    }


    @NonNull
    private final String comment;

    @NonNull
    private final String userLogin;

    @NonNull
    private final Picture picture;

    private DomainCommentary(long id, @NonNull Commentary commentary) {
        this(id,
                commentary.getVersion(),
                commentary.getComment(),
                commentary.getUserLogin(),
                commentary.getPicture());
    }

    private DomainCommentary(@NonNull Commentary commentary) {
        this(commentary.getId(), commentary);
    }

    private DomainCommentary(long id, int version, @NonNull String comment, @NonNull String userLogin,
                             @NonNull Picture picture) {
        super(id, version);
        assert StringUtils.isNotBlank(comment);
        this.comment = comment;
        this.userLogin = StringUtils.isBlank(userLogin) ? ANONYMOUS_USER : userLogin;
        this.picture = picture;
    }

    @Override
    public long getPictureId() {
        return getModelId(picture);
    }

}
