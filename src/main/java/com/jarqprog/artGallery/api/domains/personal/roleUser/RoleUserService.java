package com.jarqprog.artGallery.api.domains.personal.roleUser;


import com.jarqprog.artGallery.domain.personal.SystemRole;
import com.jarqprog.artGallery.domain.personal.User;
import com.jarqprog.artGallery.domain.personal.RoleUser;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleUserService {

    Set<RoleUser> findByUserLogin(@NonNull String userLogin);
    RoleUser addUserRole(SystemRole role, User user);

}
