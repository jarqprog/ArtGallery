package com.jarqprog.personapi.domains.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.commonapi.absmodel.DomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
