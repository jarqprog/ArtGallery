package com.jarqprog.personapi.read;

import com.jarqprog.domainperson.usecase.login.UserLogin;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import readuser.ReadUser;


@Service
public interface ReadUserService {

    ReadUser getReadUserByLogin(@NonNull UserLogin userLogin);
}
