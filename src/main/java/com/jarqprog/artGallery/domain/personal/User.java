package com.jarqprog.artGallery.domain.personal;

import com.jarqprog.artGallery.domain.DomainModel;
import lombok.NonNull;


public interface User extends DomainModel {

    long getContactId();
    String getLogin();
    String getPassword();
    void setPassword(@NonNull String password);

}
