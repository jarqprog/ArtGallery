package com.jarqprog.domainperson.model.contact;


import com.jarqprog.commondomain.absmodel.Identity;

public interface ContactData extends Identity {

    String getFirstName();
    String getLastName();
    String getEmail();
}
