package com.jarqprog.artGallery.domain.artistic;


import com.jarqprog.artGallery.domain.DomainModel;


public interface Commentary extends DomainModel {

    String getComment();
    void setComment(String content);
    String getUserLogin();
    long getPictureId();

}
