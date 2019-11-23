package com.jarqprog.artapi.domains.picture.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artdomain.model.picture.PictureData;
import com.jarqprog.commonapi.absmodel.DTO;


@JsonDeserialize(as= PictureThin.class)
public interface PictureDTO extends PictureData, DTO {
}
