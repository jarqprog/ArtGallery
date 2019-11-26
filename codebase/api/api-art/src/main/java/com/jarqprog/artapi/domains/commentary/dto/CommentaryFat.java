package com.jarqprog.artapi.domains.commentary.dto;

import com.jarqprog.artapi.domains.picture.dto.PictureFat;
import com.jarqprog.artdomain.commentary.Commentary;
import com.jarqprog.commonapi.absmodel.ApiDomainDTO;
import lombok.*;


@Data
@ToString(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class CommentaryFat extends ApiDomainDTO implements Commentary, ApiCommentaryDTO {

    private String comment;
    private String userLogin;
    private PictureFat picture;

    @Override
    public long getPictureId() {
        return getDTOId(picture);
    }

}
