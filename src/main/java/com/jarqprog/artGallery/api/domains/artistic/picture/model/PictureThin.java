package com.jarqprog.artGallery.api.domains.artistic.picture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.artGallery.api.domains.DomainDTO;
import lombok.*;

import javax.xml.bind.annotation.XmlTransient;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class PictureThin extends DomainDTO implements PictureDTO {

    private String title;
    private String path;
    private String userLogin;
    private long authorId;

    @Override
    @JsonIgnore
    @XmlTransient
    public boolean hasAuthor() {
        return authorId > 0;
    }
}
