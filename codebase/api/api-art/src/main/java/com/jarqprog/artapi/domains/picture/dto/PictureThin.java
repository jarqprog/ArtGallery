package com.jarqprog.artapi.domains.picture.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarqprog.commonapi.absmodel.ApiDomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlTransient;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class PictureThin extends ApiDomainDTO implements ApiPictureDTO {

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
