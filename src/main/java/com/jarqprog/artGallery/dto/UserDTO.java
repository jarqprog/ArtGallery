package com.jarqprog.artGallery.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserDTO implements Serializable {

    private long id;
    @JsonManagedReference private ContactDTO contact;
    @JsonManagedReference private Set<CommentaryDTO> commentaries;
    private String login;
}
