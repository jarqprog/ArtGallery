package com.jarqprog.artapi.domains.commentary.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artdomain.model.commentary.CommentaryDTO;


@JsonDeserialize(as= CommentaryThin.class)
public interface ApiCommentaryDTO extends CommentaryDTO {

}
