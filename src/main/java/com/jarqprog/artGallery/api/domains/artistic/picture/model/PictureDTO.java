package com.jarqprog.artGallery.api.domains.artistic.picture.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.api.domains.DTO;
import com.jarqprog.artGallery.domain.artistic.PictureData;

@JsonDeserialize(as=PictureThin.class)
public interface PictureDTO extends PictureData, DTO {
}
