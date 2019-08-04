package com.jarqprog.artGallery.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference private Set<PictureDTO> arts;

}
