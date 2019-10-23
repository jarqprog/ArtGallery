package com.jarqprog.artGallery.domain.dto.heavyDto;

import com.jarqprog.artGallery.domain.dto.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class PictureDTO extends DTO implements HeavyDTO {

    private String title;
    private String path;
    private AuthorDTO author;
    private UserDTO user;
}
