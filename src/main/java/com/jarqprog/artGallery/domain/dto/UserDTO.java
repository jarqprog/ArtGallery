package com.jarqprog.artGallery.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.domain.dto.thinDTO.UserThin;

import java.io.Serializable;
import java.util.Set;


@JsonDeserialize(as = UserThin.class)
public interface UserDTO extends DTO, Serializable {

    long getContactId();
    String getLogin();
    void setLogin(String login);
    String getPassword();
    void setPassword(String password);
    Set<Long> getRolesIds();

}
