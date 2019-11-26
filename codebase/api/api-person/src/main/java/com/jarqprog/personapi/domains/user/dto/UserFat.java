package com.jarqprog.personapi.domains.user.dto;

import com.jarqprog.commonapi.absmodel.ApiDomainDTO;
import com.jarqprog.domainperson.user.User;
import com.jarqprog.personapi.domains.contact.dto.ContactFat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class UserFat extends ApiDomainDTO implements ApiUserDTO, User {

    private String login;

    private String password;

    private ContactFat contact;

    @Override
    public long getContactId() {
        return getDTOId(contact);
    }

}
