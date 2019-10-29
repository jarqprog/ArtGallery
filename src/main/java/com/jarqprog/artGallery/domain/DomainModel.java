package com.jarqprog.artGallery.domain;

public interface DomainModel {

    long getId();
    void setId(long id);
    int getVersion();
    void setVersion(int version);
    boolean isNew();

}
