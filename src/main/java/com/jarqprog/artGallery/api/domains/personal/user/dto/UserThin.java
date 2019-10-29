package com.jarqprog.artGallery.api.domains.personal.user.dto;

import com.jarqprog.artGallery.api.domains.DTOThin;
import lombok.*;


@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=true)
public class UserThin extends AbstractUserDTO implements DTOThin {

    @Setter
    private long contactId;

    public UserThin(@NonNull String login) {
        super(login);
    }

    public UserThin(@NonNull String login, long contactId) {
        super(login);
        this.contactId = contactId;
    }
}
