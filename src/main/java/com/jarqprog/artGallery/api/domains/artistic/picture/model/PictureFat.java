package com.jarqprog.artGallery.api.domains.artistic.picture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.artGallery.api.domains.DomainDTO;
import com.jarqprog.artGallery.api.domains.artistic.author.dto.AuthorFat;
import com.jarqprog.artGallery.domain.artistic.Picture;
import lombok.*;

import javax.xml.bind.annotation.XmlTransient;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class PictureFat extends DomainDTO implements PictureDTO, Picture {

    private String title;
    private String path;
    private String userLogin;
    private AuthorFat author;

    @Override
    public long getAuthorId() {
        return getDTOId(author);
    }

    @JsonIgnore
    @XmlTransient
    @Override
    public boolean hasAuthor() {
        return author != null;
    }
}
