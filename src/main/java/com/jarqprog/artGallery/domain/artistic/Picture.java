package com.jarqprog.artGallery.domain.artistic;

import com.jarqprog.artGallery.domain.DomainModel;


public interface Picture extends DomainModel {


    String getTitle();
    void setTitle(String title);
    String getPath();
    void setPath(String path);
    long getAuthorId();
    String getUserLogin();

}
