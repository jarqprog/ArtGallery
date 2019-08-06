package com.jarqprog.artGallery.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class CommentaryDTO extends DTO {

    private String content;
    private UserDTO user;
    private PictureDTO picture;
}
