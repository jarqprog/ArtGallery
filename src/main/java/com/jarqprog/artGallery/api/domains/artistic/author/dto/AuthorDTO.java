package com.jarqprog.artGallery.api.domains.artistic.author.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.api.domains.DTO;
import com.jarqprog.artGallery.domain.artistic.Author;

@JsonDeserialize(as=AuthorThin.class)
public interface AuthorDTO extends Author, DTO {

}
