package com.jarqprog.domainperson.contact;


import com.jarqprog.commondomain.Identity;

public interface ContactData extends Identity {

    String getFirstName();
    String getLastName();
    String getEmail();
}
