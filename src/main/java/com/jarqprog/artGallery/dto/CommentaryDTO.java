package com.jarqprog.artGallery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentaryDTO implements DTO {

    private long id;
    private String content;
    private UserDTO userDTO;
    private PictureDTO pictureDTO;
}
