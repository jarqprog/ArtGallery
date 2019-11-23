package com.jarqprog.artdomain.model.author;


import com.jarqprog.commondomain.absmodel.Identity;

public interface AuthorData extends Identity {

    String ANONYMOUS = "anonymous";

    String getArtisticNickname();
    long getContactId();

}
