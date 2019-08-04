package com.jarqprog.artGallery.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class AuthorDTO implements Serializable {

    private long id;
    private String artisticNickname;
    private ContactDTO contact;
    @JsonIgnore private Set<PictureDTO> arts;
}
