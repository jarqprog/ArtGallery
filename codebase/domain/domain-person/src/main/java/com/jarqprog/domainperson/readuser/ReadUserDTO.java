package com.jarqprog.domainperson.readuser;

import com.jarqprog.commondomain.absmodel.DTO;
import com.jarqprog.domainperson.SystemRole;
import com.jarqprog.domainperson.contact.ContactDTO;
import com.jarqprog.domainperson.user.UserDTO;

import java.util.Set;

public interface ReadUserDTO extends DTO {

    UserDTO user();
    ContactDTO contact();
    Set<SystemRole> roles();
}
