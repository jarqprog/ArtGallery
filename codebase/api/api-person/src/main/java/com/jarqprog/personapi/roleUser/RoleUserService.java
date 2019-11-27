package com.jarqprog.personapi.roleUser;


import com.jarqprog.domainperson.SystemRole;
import com.jarqprog.domainperson.user.UserData;
import com.jarqprog.personapi.roleUser.dto.ApiRoleUserDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleUserService {

    Set<ApiRoleUserDTO> findByUserLogin(@NonNull String userLogin);
    ApiRoleUserDTO addUserRole(SystemRole role, UserData userData);

}
