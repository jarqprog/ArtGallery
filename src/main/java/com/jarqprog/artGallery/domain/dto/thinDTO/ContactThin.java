package com.jarqprog.artGallery.domain.dto.thinDTO;

import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.DomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ContactThin extends DomainDTO implements ContactDTO, DTOThin {

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
}
