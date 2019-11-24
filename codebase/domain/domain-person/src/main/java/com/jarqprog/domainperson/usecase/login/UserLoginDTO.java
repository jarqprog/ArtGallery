package com.jarqprog.domainperson.usecase.login;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Accessors(fluent = true)
public class UserLoginDTO implements UserLogin {

    @Builder(builderMethodName = "createWith")
    public static UserLogin buildWithData(@NonNull final String login,
                                           @NonNull final String password) {
        return new UserLoginDTO(login, password);
    }

    @NonNull private String login;
    @NonNull private String password;
}
