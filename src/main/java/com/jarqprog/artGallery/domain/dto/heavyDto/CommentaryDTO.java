package com.jarqprog.artGallery.domain.dto.heavyDto;

import com.jarqprog.artGallery.domain.dto.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class CommentaryDTO extends DTO  implements HeavyDTO {

    private String comment;
    private UserDTO user;
    private PictureDTO picture;

}
