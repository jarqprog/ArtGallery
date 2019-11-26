package com.jarqprog.domainperson.user;

import com.jarqprog.commondomain.Identity;

public interface UserData extends Identity {

    long getContactId();
    String getLogin();
    String getPassword();
}
