package com.jarqprog.domainperson.model.user;


import com.jarqprog.commondomain.absmodel.Model;
import com.jarqprog.domainperson.model.contact.Contact;

public interface User extends UserData, Model {

    Contact getContact();

}
