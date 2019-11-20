package com.jarqprog.personapi.domains.contact.dto;

import com.jarqprog.commonapi.absmodel.DomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class ContactThin extends DomainDTO implements ContactDTO {

    private String firstName;
    private String lastName;
    private String email;
}
