package com.jarqprog.artdomain.author;

import com.jarqprog.commondomain.IdentityModel;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DomainAuthor extends IdentityModel implements Author {

    public static DomainAuthor mergeID(long id, @NonNull final Author author) {
        assert id > 0;
        return new DomainAuthor(id, author);
    }

    public static DomainAuthor fromAuthor(@NonNull final Author author) {
        return new DomainAuthor(author);
    }

    @Builder(builderMethodName = "createWith")
    public static DomainAuthor buildWithData(long id, int version, @NonNull String artisticNickname, long contactId) {
        return new DomainAuthor(id, version, artisticNickname, contactId);
    }


    @NonNull
    private final String artisticNickname;

    private final long contactId;

    private DomainAuthor(@NonNull final Author author) {
        this(author.getId(),
            author.getVersion(),
            author.getArtisticNickname(),
            author.getContactId());
    }

    private DomainAuthor(long id, @NonNull final Author author) {
        this(id,
            author.getVersion(),
            author.getArtisticNickname(),
            author.getContactId());
    }

    private DomainAuthor(long id, int version, String artisticNickname, long contactId) {
        super(id, version);
        assert StringUtils.isNotEmpty(artisticNickname);
        this.artisticNickname = artisticNickname;
        this.contactId = contactId;
    }


}
