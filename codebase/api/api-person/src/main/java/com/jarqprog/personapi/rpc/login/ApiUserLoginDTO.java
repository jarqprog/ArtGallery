package com.jarqprog.personapi.rpc.login;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jarqprog.domainperson.login.UserLoginDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ApiUserLoginDTO implements UserLoginDTO {

    @JsonInclude
    @NonNull
    private String login;

    @NonNull private String password;
}
