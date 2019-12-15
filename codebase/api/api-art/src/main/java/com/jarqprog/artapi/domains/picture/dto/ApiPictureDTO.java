package com.jarqprog.artapi.domains.picture.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artdomain.picture.PictureDTO;


@JsonDeserialize(as= PictureThin.class)
public interface ApiPictureDTO extends PictureDTO {
}
