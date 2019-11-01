package com.jarqprog.artGallery.api.domains.personal.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.artGallery.api.domains.DomainDTO;
import com.jarqprog.artGallery.api.domains.personal.contact.dto.ContactFat;

import com.jarqprog.artGallery.domain.personal.User;
import lombok.*;

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

    @JsonIgnore
    @XmlTransient
    @Override
    public boolean hasContact() {
        return contact != null;
    }
}
