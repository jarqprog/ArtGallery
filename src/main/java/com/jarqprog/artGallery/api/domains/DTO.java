package com.jarqprog.artGallery.api.domains;

import com.jarqprog.artGallery.domain.Identity;

import java.io.Serializable;

public interface DTO extends Identity, Serializable {

    int getVersion();
    void setVersion(int version);
    boolean isNew();

}
