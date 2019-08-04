package com.jarqprog.artGallery.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class PictureDTO implements Serializable {

    private long id;
    private String title;
    @JsonBackReference private AuthorDTO author;
    @JsonManagedReference private Set<CommentaryDTO> commentaries;
}
