package com.jarqprog.artGallery.api.domains;

import com.jarqprog.artGallery.domain.Identity;

import java.io.Serializable;

public interface DTO extends Identity, Serializable {

    long getId();
    void setId(long id);
    int getVersion();
    void setVersion(int version);
    boolean isNew();

}
