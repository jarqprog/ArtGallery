package com.jarqprog.artGallery.api.domains.personal.user.dto;

import com.jarqprog.artGallery.api.domains.DTOFat;
import com.jarqprog.artGallery.api.domains.personal.contact.dto.ContactFat;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=true)
public class UserFat extends AbstractUserDTO implements DTOFat {

    @Setter
    private ContactFat contact;

    public UserFat(@NonNull String login) {
        super(login);
    }

    public UserFat(@NonNull String login, @NonNull ContactFat contact) {
        super(login);
        this.contact = contact;
    }

    @Override
    public long getContactId() {
        return getDTOId(contact);
    }
}
