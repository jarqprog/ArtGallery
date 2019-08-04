package com.jarqprog.artGallery.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference private UserDTO user;
    @JsonManagedReference private AuthorDTO author;
}
