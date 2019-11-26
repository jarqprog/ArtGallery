package com.jarqprog.domainperson.user;


import com.jarqprog.commondomain.Model;
import com.jarqprog.domainperson.contact.Contact;

public interface User extends UserData, Model {

    Contact getContact();

}
