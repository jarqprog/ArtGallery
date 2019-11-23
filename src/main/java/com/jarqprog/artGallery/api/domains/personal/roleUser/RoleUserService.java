package com.jarqprog.artGallery.api.domains.personal.roleUser;


import com.jarqprog.artGallery.api.domains.personal.roleUser.dto.RoleUserDTO;
import com.jarqprog.artGallery.domain.personal.SystemRole;
import com.jarqprog.artGallery.domain.personal.UserData;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleUserService {

    Set<RoleUserDTO> findByUserLogin(@NonNull String userLogin);
    RoleUserDTO addUserRole(SystemRole role, UserData userData);

}
