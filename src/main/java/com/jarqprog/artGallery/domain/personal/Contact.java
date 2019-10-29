package com.jarqprog.artGallery.domain.personal;

import com.jarqprog.artGallery.domain.DomainModel;


public interface Contact extends DomainModel {

    String getFirstName();
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    String getNickname();
    void setNickname(String nickname);
    String getEmail();
    void setEmail(String email);
}
