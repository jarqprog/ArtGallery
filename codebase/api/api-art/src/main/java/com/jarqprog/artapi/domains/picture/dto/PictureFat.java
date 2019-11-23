package com.jarqprog.artapi.domains.picture.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.artapi.domains.author.dto.AuthorFat;
import com.jarqprog.artdomain.model.picture.Picture;
import com.jarqprog.commonapi.absmodel.DomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
