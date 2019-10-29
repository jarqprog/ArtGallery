package com.jarqprog.artGallery.api.domains.artistic.author.dto;

import com.jarqprog.artGallery.api.domains.DTOFat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class AuthorFat extends AbstractAuthorDTO implements DTOFat {

    public AuthorFat(@NonNull String artisticNickname) {
        super(artisticNickname);
    }

    public AuthorFat(@NonNull String artisticNickname, long contactId) {
        super(artisticNickname, contactId);
    }
}
