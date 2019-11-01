package com.jarqprog.artGallery.domain.artistic;

import com.jarqprog.artGallery.domain.Identity;


public interface AuthorData extends Identity {

    String ANONYMOUS = "anonymous";

    String getArtisticNickname();
    long getContactId();

}
