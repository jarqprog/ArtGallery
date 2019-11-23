package com.jarqprog.artapi.domains.author;


import com.jarqprog.commonapi.absmodel.DomainEntity;
import com.jarqprog.artdomain.model.author.Author;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Access(AccessType.FIELD)
@Table(name="authors")
public class AuthorEntity extends DomainEntity implements Author {

    @NonNull
    @NotNull
    private String artisticNickname;

    private long contactId;

    public static AuthorEntity fromAuthor(@NonNull final Author author) {
        return new AuthorEntity(author);
    }

    private AuthorEntity(@NonNull final Author author) {
        this(author.getId(), author.getVersion(), author.getArtisticNickname(), author.getContactId());
    }

    private AuthorEntity(long id, int version, String artisticNickname, long contactId) {
        super(id, version);
        assert StringUtils.isNotEmpty(artisticNickname);
        this.artisticNickname = artisticNickname;
        this.contactId = contactId;
    }
}
