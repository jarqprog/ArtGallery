package com.jarqprog.artGallery.api.domains.personal.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.artGallery.api.domains.DomainDTO;
import lombok.*;

import javax.xml.bind.annotation.XmlTransient;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper=true)
abstract class AbstractUserDTO extends DomainDTO implements UserDTO {

    @NonNull
    private String login;

    @Setter
    @JsonIgnore
    @XmlTransient
    private String password;

    public AbstractUserDTO(@NonNull String login) {
        this.login = login;
    }


}
