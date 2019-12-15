package com.jarqprog.domainperson.readuser;

import com.jarqprog.domainperson.SystemRole;
import com.jarqprog.domainperson.contact.ContactData;
import com.jarqprog.domainperson.user.UserData;

import java.util.Set;

public interface ReadUser {

    UserData user();
    ContactData contact();
    Set<SystemRole> roles();
}
