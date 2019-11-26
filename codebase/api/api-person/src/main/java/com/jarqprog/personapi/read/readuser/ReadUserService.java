package com.jarqprog.personapi.read.readuser;

import com.jarqprog.domainperson.login.UserLoginDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;


@Service
public interface ReadUserService {

    ApiReadUserDTO getReadUserByLogin(@NonNull UserLoginDTO userLogin);
}
