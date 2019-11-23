package com.jarqprog.artGallery.domain.personal;

import com.jarqprog.artGallery.domain.Identity;


public interface ContactData extends Identity {

    String getFirstName();
    String getLastName();
    String getEmail();
}
