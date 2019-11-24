package com.jarqprog.artapi.domains.author.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artdomain.model.author.AuthorDTO;

@JsonDeserialize(as=AuthorThin.class)
public interface ApiAuthorDTO extends AuthorDTO {

}
