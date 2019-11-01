package com.jarqprog.artGallery.api.domains.personal.roleUser;


import com.jarqprog.artGallery.domain.personal.RoleUserData;
import com.jarqprog.artGallery.domain.personal.SystemRole;
import com.jarqprog.artGallery.domain.personal.UserData;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleUserService {

    Set<RoleUserData> findByUserLogin(@NonNull String userLogin);
    RoleUserData addUserRole(SystemRole role, UserData userData);

}
