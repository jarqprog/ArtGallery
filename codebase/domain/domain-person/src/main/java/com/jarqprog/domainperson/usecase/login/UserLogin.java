package com.jarqprog.domainperson.usecase.login;

import java.io.Serializable;

public interface UserLogin extends Serializable {

    String login();
    String password();
}
