package com.jarqprog.personapi.read.readuser;

import readuser.ReadUser;

public interface ReadUserDTOConverter {

    ApiReadUserDTO mapToDTO(ReadUser domainReadUser);

}
