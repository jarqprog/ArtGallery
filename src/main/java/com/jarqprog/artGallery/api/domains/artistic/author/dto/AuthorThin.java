package com.jarqprog.artGallery.api.domains.artistic.author.dto;

import com.jarqprog.artGallery.api.domains.DTOThin;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class AuthorThin extends AbstractAuthorDTO implements DTOThin {

    public AuthorThin(@NonNull String artisticNickname) {
        super(artisticNickname);
    }

    public AuthorThin(@NonNull String artisticNickname, long contactId) {
        super(artisticNickname, contactId);
    }
}
