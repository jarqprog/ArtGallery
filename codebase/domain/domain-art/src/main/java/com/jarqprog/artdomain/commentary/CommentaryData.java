package com.jarqprog.artdomain.commentary;


import com.jarqprog.commondomain.Identity;

public interface CommentaryData extends Identity {

    String ANONYMOUS_USER = "anonymous";

    String getComment();
    String getUserLogin();
    long getPictureId();

}
