package com.jarqprog.artGallery.domain.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.domain.dto.thinDTO.CommentaryThin;

import java.io.Serializable;

@JsonDeserialize(as = CommentaryThin.class)
public interface CommentaryDTO  extends DTO, Serializable {
    
    String getComment();
    void setComment(String content);
    long getUserId();
    long getPictureId();

}
