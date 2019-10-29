package com.jarqprog.artGallery.api.domains.personal.contact.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper=true)
abstract class AbstractContactDTO extends DomainDTO implements ContactDTO {

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;

    public AbstractContactDTO(@NonNull String firstName, @NonNull String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public AbstractContactDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
