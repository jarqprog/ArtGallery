package com.jarqprog.artGallery.domain.dto.lightDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.artGallery.domain.dto.DTO;
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
public class UserDTOLight extends DTO implements LightDTO {

    private long contactId;
    private String login;
    @JsonIgnore @XmlTransient private String password;
    @JsonIgnore @XmlTransient private Set<Long> rolesIds;

    public UserDTOLight(long roleId) {
        this.rolesIds = new HashSet<>();
        this.rolesIds.add(roleId);
    }

    public boolean hasRole(long role) {
        return this.rolesIds.contains(role);
    }

    public void addRole(long role) {
        this.rolesIds.add(role);
    }

    public void removeRole(long role) {
        this.rolesIds.remove(role);
    }
}
