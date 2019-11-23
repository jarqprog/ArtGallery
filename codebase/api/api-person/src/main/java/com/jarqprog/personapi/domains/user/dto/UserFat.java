package com.jarqprog.personapi.domains.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.commonapi.absmodel.DomainDTO;
import com.jarqprog.domainperson.model.user.User;
import com.jarqprog.personapi.domains.contact.dto.ContactFat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlTransient;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class UserFat extends DomainDTO implements UserDTO, User {

    private String login;

    @JsonIgnore
    @XmlTransient
    private String password;

    private ContactFat contact;

    @Override
    public long getContactId() {
        return getDTOId(contact);
    }

}
