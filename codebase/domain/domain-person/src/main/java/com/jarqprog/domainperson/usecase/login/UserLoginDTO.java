package com.jarqprog.domainperson.usecase.login;

import java.io.Serializable;

public interface UserLoginDTO extends Serializable {

    String login();
    String password();

}
