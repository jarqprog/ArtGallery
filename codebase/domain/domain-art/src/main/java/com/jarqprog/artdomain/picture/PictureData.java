package com.jarqprog.artdomain.picture;


import com.jarqprog.commondomain.Identity;

public interface PictureData extends Identity {

    String UNTITLED = "untitled";
    String ANONYMOUS_USER = "anonymous";


    String getTitle();
    String getPath();
    long getAuthorId();
    String getUserLogin();
    boolean hasAuthor();

}
