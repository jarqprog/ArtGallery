package com.jarqprog.artGallery.api.domains.personal.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.artGallery.api.domains.DomainDTO;
import lombok.*;

import javax.xml.bind.annotation.XmlTransient;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class UserThin extends DomainDTO implements UserDTO {

    private String login;

    @JsonIgnore
    @XmlTransient
    private String password;

    private long contactId;

}
