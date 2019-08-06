package com.jarqprog.artGallery.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentaryDTO extends DTO {

    private String content;
    private UserDTO user;
    private PictureDTO picture;
}
