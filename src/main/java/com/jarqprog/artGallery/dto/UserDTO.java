package com.jarqprog.artGallery.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO implements DTO {

    private long id;
    private ContactDTO contact;
    @JsonIgnore private Set<CommentaryDTO> commentaries;
    private String login;
}
