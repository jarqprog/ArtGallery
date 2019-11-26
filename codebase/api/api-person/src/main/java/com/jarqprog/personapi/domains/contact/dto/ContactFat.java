package com.jarqprog.personapi.domains.contact.dto;

import com.jarqprog.commonapi.absmodel.ApiDomainDTO;
import com.jarqprog.domainperson.contact.Contact;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class ContactFat extends ApiDomainDTO implements ApiContactDTO, Contact {

    private String firstName;
    private String lastName;
    private String email;
}
