package com.jarqprog.artGallery.api.domains.personal.contact.dto;

import com.jarqprog.artGallery.api.domains.DTOFat;

import lombok.*;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=true)
public class ContactFat extends AbstractContactDTO implements DTOFat {

    public ContactFat(@NonNull String firstName, @NonNull String email) {
        super(firstName, email);
    }

    public ContactFat(@NonNull String firstName, @NonNull String lastName, @NonNull String email) {
        super(firstName, lastName, email);
    }
}
