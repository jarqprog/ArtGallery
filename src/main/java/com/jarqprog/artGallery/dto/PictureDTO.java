package com.jarqprog.artGallery.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PictureDTO extends DTO {

    private String title;
    private AuthorDTO author;
}
