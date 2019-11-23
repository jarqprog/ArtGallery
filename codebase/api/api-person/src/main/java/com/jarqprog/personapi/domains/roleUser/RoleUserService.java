package com.jarqprog.personapi.domains.roleUser;


import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.user.UserData;
import com.jarqprog.personapi.domains.roleUser.dto.RoleUserDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleUserService {

    Set<RoleUserDTO> findByUserLogin(@NonNull String userLogin);
    RoleUserDTO addUserRole(SystemRole role, UserData userData);

}
