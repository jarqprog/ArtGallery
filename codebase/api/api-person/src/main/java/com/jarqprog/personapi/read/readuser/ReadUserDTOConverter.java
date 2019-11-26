package com.jarqprog.personapi.read.readuser;

import com.jarqprog.domainperson.readuser.ReadUser;

public interface ReadUserDTOConverter {

    ApiReadUserDTO mapToDTO(ReadUser domainReadUser);

}
