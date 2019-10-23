package com.jarqprog.artGallery.domain.dto.heavyDto;

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
public class UserDTO extends DTO implements HeavyDTO {

    private ContactDTO contact;
    private String login;
    @JsonIgnore @XmlTransient private String password;
    @JsonIgnore @XmlTransient private Set<RoleDTO> roles;

    public UserDTO(RoleDTO roleDTO) {
        this.roles = new HashSet<>();
        this.roles.add(roleDTO);
    }

    public boolean hasRole(RoleDTO role) {
        return this.roles.contains(role);
    }

    public void addRole(RoleDTO role) {
        this.roles.add(role);
    }

    public void removeRole(RoleDTO role) {
        this.roles.remove(role);
    }
}
