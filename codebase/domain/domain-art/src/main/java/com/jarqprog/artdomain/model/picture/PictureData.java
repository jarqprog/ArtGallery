package com.jarqprog.artdomain.model.picture;


import com.jarqprog.commondomain.absmodel.Identity;

public interface PictureData extends Identity {

    String UNTITLED = "untitled";
    String ANONYMOUS_USER = "anonymous";


    String getTitle();
    String getPath();
    long getAuthorId();
    String getUserLogin();
    boolean hasAuthor();

}
