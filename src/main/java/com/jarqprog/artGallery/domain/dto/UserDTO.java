package com.jarqprog.artGallery.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserDTO extends DTO {

    private ContactDTO contact;
    private String login;
    @JsonIgnore @XmlTransient private String password;
    @JsonIgnore @XmlTransient private Set<RoleDTO> roles;

    public UserDTO(RoleDTO roleDTO) {
        this.roles = new HashSet<>();
        this.roles.add(roleDTO);
    }
}
