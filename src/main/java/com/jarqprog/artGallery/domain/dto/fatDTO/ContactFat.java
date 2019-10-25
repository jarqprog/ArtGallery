package com.jarqprog.artGallery.domain.dto.fatDTO;

import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.DomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class ContactFat extends DomainDTO implements ContactDTO, DTOFat {

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
}
