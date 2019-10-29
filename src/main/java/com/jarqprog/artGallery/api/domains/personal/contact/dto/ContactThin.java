package com.jarqprog.artGallery.api.domains.personal.contact.dto;

import com.jarqprog.artGallery.api.domains.DTOThin;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=true)
public class ContactThin extends AbstractContactDTO implements DTOThin {

    public ContactThin(@NonNull String firstName, @NonNull String email) {
        super(firstName, email);
    }

    public ContactThin(@NonNull String firstName, @NonNull String lastName, @NonNull String email) {
        super(firstName, lastName, email);
    }
}
