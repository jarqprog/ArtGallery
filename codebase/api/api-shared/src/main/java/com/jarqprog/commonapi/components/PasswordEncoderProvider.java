package com.jarqprog.commonapi.components;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface PasswordEncoderProvider {

    PasswordEncoder provide();

}
