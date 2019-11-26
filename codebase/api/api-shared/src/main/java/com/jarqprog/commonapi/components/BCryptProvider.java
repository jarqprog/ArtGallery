package com.jarqprog.commonapi.components;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BCryptProvider implements PasswordEncoderProvider {

    public static PasswordEncoderProvider build() {
        return new BCryptProvider();
    }

    @Override
    public PasswordEncoder provide() {
        return new BCryptPasswordEncoder();
    }
}
