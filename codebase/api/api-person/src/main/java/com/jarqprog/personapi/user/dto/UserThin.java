package com.jarqprog.personapi.user.dto;

import com.jarqprog.commonapi.absmodel.ApiDomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class UserThin extends ApiDomainDTO implements ApiUserDTO {

    private String login;

    private String password;

    private long contactId;

}
