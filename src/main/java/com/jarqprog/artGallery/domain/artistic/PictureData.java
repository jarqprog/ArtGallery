package com.jarqprog.artGallery.domain.artistic;

import com.jarqprog.artGallery.domain.Identity;


public interface PictureData extends Identity {

    String UNTITLED = "untitled";
    String ANONYMOUS_USER = "anonymous";


    String getTitle();
    String getPath();
    long getAuthorId();
    String getUserLogin();
    boolean hasAuthor();

}
