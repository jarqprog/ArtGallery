package com.jarqprog.artGallery.api.domains.personal.contact.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import com.jarqprog.artGallery.domain.personal.Contact;
import lombok.*;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class ContactFat extends DomainDTO implements ContactDTO, Contact {

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
}
