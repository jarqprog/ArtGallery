package com.jarqprog.artGallery.api.domains.personal.contact.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import lombok.*;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class ContactThin extends DomainDTO implements ContactDTO {

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
}
