package com.jarqprog.artGallery.domain.dto.fatDTO;

import com.jarqprog.artGallery.domain.dto.DomainDTO;
import com.jarqprog.artGallery.domain.dto.PictureDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class PictureFat extends DomainDTO implements PictureDTO, DTOFat {

    private String title;
    private String path;
    private AuthorFat author;
    private UserFat user;


    @Override
    public long getAuthorId() {
        return author != null ? author.getId() : 0;
    }

    @Override
    public long getUserId() {
        return user != null ? user.getId() : 0;
    }
}
