package com.jarqprog.personapi.domains.roleUser;


import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.user.UserData;
import com.jarqprog.personapi.domains.roleUser.dto.ApiRoleUserDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleUserService {

    Set<ApiRoleUserDTO> findByUserLogin(@NonNull String userLogin);
    ApiRoleUserDTO addUserRole(SystemRole role, UserData userData);

}
