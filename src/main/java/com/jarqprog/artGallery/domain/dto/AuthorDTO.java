package com.jarqprog.artGallery.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.domain.dto.thinDTO.AuthorThin;

import java.io.Serializable;

@JsonDeserialize(as = AuthorThin.class)
public interface AuthorDTO extends DTO, Serializable {

    String getArtisticNickname();
    void setArtisticNickname(String artisticNickname);
    public long getContactId();

}
