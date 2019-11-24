package com.jarqprog.personapi.domains.contact.dto;

import com.jarqprog.commonapi.absmodel.ApiDomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class ContactThin extends ApiDomainDTO implements ApiContactDTO {

    private String firstName;
    private String lastName;
    private String email;
}
