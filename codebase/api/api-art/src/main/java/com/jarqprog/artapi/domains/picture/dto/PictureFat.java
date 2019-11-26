package com.jarqprog.artapi.domains.picture.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.artapi.domains.author.dto.AuthorFat;
import com.jarqprog.artdomain.picture.Picture;
import com.jarqprog.commonapi.absmodel.ApiDomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlTransient;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class PictureFat extends ApiDomainDTO implements ApiPictureDTO, Picture {

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
