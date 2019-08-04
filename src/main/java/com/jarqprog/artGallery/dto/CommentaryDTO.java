package com.jarqprog.artGallery.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommentaryDTO implements Serializable {

    private long id;
    private String content;
    @JsonBackReference private UserDTO user;
    @JsonBackReference private PictureDTO picture;

}
