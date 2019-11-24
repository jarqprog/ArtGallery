package com.jarqprog.personapi.read;

import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.model.user.User;
import lombok.Data;
import readuser.ReadUser;

import java.util.Optional;
import java.util.Set;

@Data
public class ReadUserDTOImpl implements ReadUser {

    private User user;
    private Contact contact;
    private Set<SystemRole> roles;

    @Override
    public User user() {
        return user;
    }

    @Override
    public Optional<Contact> contact() {
        return Optional.empty();
    }

    @Override
    public Set<SystemRole> roles() {
        return null;
    }
}
