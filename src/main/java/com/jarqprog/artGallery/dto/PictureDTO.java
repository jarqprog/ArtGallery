package com.jarqprog.artGallery.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class PictureDTO implements Serializable {

    private long id;
    private String title;
    private AuthorDTO author;
    @JsonIgnore private Set<CommentaryDTO> commentaries;
}
