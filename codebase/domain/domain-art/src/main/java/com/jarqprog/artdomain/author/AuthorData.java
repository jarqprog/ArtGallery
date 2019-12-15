package com.jarqprog.artdomain.author;


import com.jarqprog.commondomain.Identity;

public interface AuthorData extends Identity {

    String ANONYMOUS = "anonymous";

    String getArtisticNickname();
    long getContactId();

}
