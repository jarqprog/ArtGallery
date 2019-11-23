package com.jarqprog.artGallery.domain.artistic;


import com.jarqprog.artGallery.domain.Identity;


public interface CommentaryData extends Identity {

    String ANONYMOUS_USER = "anonymous";

    String getComment();
    String getUserLogin();
    long getPictureId();

}
