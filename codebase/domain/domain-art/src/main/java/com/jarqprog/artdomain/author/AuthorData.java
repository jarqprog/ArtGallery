package com.jarqprog.artdomain.author;


import com.jarqprog.commondomain.absmodel.Identity;

public interface AuthorData extends Identity {

    String ANONYMOUS = "anonymous";

    String getArtisticNickname();
    long getContactId();

}
