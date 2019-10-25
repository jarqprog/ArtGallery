package com.jarqprog.artGallery.domain.dto.thinDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.artGallery.domain.dto.DomainDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserThin extends DomainDTO implements UserDTO, DTOThin {

    private String login;
    @JsonIgnore @XmlTransient private String password;
    private Set<Long> rolesIds = new HashSet<>();
    private long contactId;

    public UserThin(long roleId) {
        this.rolesIds.add(roleId);
    }

    public UserThin(long roleId, long contactId) {
        this(roleId);
        this.contactId = contactId;
    }
}
