package com.jarqprog.artGallery.domain.artistic;

import com.jarqprog.artGallery.domain.DomainModel;


public interface Author extends DomainModel {

    String ANONYMOUS = "anonymous";

    String getArtisticNickname();
    void setArtisticNickname(String artisticNickname);
    long getContactId();

}
