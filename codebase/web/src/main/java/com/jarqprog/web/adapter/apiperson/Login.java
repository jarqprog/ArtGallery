package com.jarqprog.web.adapter.apiperson;

import com.jarqprog.domainperson.model.user.User;

public interface Login {

    User retrieveUser(final String userLogin);

}
