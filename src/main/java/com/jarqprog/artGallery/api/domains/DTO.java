package com.jarqprog.artGallery.api.domains;

import com.jarqprog.artGallery.domain.DomainModel;

import java.io.Serializable;

public interface DTO extends DomainModel, Serializable {

    int getVersion();
    void setVersion(int version);
    boolean isNew();

}
