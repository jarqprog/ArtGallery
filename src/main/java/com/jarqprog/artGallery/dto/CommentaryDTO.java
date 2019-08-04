package com.jarqprog.artGallery.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommentaryDTO implements Serializable {

    private long id;
    private String content;
    private UserDTO userDTO;
    private PictureDTO pictureDTO;
}
