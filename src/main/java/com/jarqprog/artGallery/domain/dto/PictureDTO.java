package com.jarqprog.artGallery.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class PictureDTO extends DTO {

    private String title;
    private String path;
    private AuthorDTO author;
    private UserDTO user;
}
