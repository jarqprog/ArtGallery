package com.jarqprog.personapi.readuser;

import com.jarqprog.domainperson.readuser.ReadUser;

public interface ReadUserDTOConverter {

    ApiReadUserDTO mapToDTO(ReadUser domainReadUser);

}
