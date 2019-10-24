package com.jarqprog.artGallery.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.domain.dto.thinDTO.PictureThin;

import java.io.Serializable;

@JsonDeserialize(as = PictureThin.class)
public interface PictureDTO extends DTO, Serializable {

    String getTitle();
    void setTitle(String title);
    String getPath();
    void setPath(String path);
    long getAuthorId();
    long getUserId();

}
