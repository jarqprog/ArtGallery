package com.jarqprog.artGallery.domain.personal;

import com.jarqprog.artGallery.domain.Identity;


public interface UserData extends Identity {

    long getContactId();
    String getLogin();
    String getPassword();
}
