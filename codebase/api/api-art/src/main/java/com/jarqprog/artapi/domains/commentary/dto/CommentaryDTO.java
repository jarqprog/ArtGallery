package com.jarqprog.artapi.domains.commentary.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artdomain.model.commentary.CommentaryData;
import com.jarqprog.commonapi.absmodel.DTO;


@JsonDeserialize(as= CommentaryThin.class)
public interface CommentaryDTO extends CommentaryData, DTO {

}
