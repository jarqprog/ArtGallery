package com.jarqprog.artGallery.domain.dto.fatDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.artGallery.domain.dto.DomainDTO;
import com.jarqprog.artGallery.domain.dto.RoleDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserFat extends DomainDTO implements UserDTO, DTOFat {

    private String login;

    @JsonIgnore @XmlTransient private String password;
    @JsonIgnore @XmlTransient private Set<RoleFat> roles = new HashSet<>();

    private ContactFat contact;

    public UserFat(RoleFat roleDTO) {
        this.roles.add(roleDTO);
    }

    @Override
    public long getContactId() {
        return contact != null ? contact.getId() : 0;
    }

    @Override
    public Set<Long> getRolesIds() {
        return roles.stream()
                .map(RoleDTO::getId)
                .collect(Collectors.toSet());
    }
}
