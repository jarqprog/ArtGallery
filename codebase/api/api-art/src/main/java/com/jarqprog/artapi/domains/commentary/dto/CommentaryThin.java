package com.jarqprog.artapi.domains.commentary.dto;

import com.jarqprog.commonapi.absmodel.DomainDTO;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class CommentaryThin extends DomainDTO implements CommentaryDTO {

    private String comment;
    private String userLogin;
    private long pictureId;


    @Builder(builderMethodName = "createWith")
    public static CommentaryThin builder(long id, int version, String comment, String userLogin, long pictureId) {
        CommentaryThin commentary = new CommentaryThin(comment, userLogin, pictureId);
        commentary.setId(id);
        commentary.setVersion(version);
        return commentary;
    }

}
