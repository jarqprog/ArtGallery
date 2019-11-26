package com.jarqprog.domainperson.login;

import com.jarqprog.commondomain.DTO;

public interface UserLoginDTO extends DTO {

    String login();
    String password();

}
