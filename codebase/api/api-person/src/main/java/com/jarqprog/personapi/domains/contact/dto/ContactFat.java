package com.jarqprog.personapi.domains.contact.dto;

import com.jarqprog.commonapi.absmodel.DomainDTO;
import com.jarqprog.domainperson.model.contact.Contact;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class ContactFat extends DomainDTO implements ContactDTO, Contact {

    private String firstName;
    private String lastName;
    private String email;
}
