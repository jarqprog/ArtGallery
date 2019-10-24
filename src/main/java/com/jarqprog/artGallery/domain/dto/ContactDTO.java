package com.jarqprog.artGallery.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.domain.dto.thinDTO.ContactThin;

import java.io.Serializable;

@JsonDeserialize(as = ContactThin.class)
public interface ContactDTO extends DTO, Serializable {

    String getFirstName();
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    String getNickname();
    void setNickname(String nickname);
    String getEmail();
    void setEmail(String email);
}
