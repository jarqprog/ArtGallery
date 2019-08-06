package com.jarqprog.artGallery.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PictureDTO implements DTO {

    private long id;
    private String title;
    private AuthorDTO author;
}
