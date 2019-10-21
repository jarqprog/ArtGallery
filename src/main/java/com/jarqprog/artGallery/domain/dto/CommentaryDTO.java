package com.jarqprog.artGallery.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class CommentaryDTO extends DTO {

    private String comment;
    private UserDTO user;
    private PictureDTO picture;

}
