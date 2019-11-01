package com.jarqprog.artGallery.api.domains.artistic.commentary.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.api.domains.DTO;
import com.jarqprog.artGallery.domain.artistic.CommentaryData;

@JsonDeserialize(as=CommentaryThin.class)
public interface CommentaryDTO extends CommentaryData, DTO {

}
