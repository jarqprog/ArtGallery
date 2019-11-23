package com.jarqprog.domainperson.model.user;

import com.jarqprog.commondomain.absmodel.Identity;

public interface UserData extends Identity {

    long getContactId();
    String getLogin();
    String getPassword();
}
