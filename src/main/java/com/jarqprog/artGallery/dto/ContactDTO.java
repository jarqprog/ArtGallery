package com.jarqprog.artGallery.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ContactDTO implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    @JsonIgnore private UserDTO user;
    @JsonIgnore private AuthorDTO author;
}
