package com.jarqprog.artGallery.api.domains.artistic.author.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
abstract class AbstractAuthorDTO extends DomainDTO implements AuthorDTO {

    private String artisticNickname = ANONYMOUS;
    private long contactId;

    AbstractAuthorDTO(@NonNull String artisticNickname) {
        this.artisticNickname = artisticNickname;
    }

    AbstractAuthorDTO(@NonNull String artisticNickname, long contactId) {
        this(artisticNickname);
        this.contactId = contactId;
    }

}
