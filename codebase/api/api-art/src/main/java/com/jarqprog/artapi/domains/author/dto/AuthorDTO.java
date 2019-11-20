package com.jarqprog.artapi.domains.author.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artdomain.model.author.AuthorData;
import com.jarqprog.commonapi.absmodel.DTO;

@JsonDeserialize(as=AuthorThin.class)
public interface AuthorDTO extends AuthorData, DTO {

}
