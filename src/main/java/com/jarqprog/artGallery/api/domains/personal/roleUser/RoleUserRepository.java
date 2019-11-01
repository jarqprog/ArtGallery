package com.jarqprog.artGallery.api.domains.personal.roleUser;

import com.jarqprog.artGallery.domain.personal.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;
import java.util.Set;

@Repository
@EnableTransactionManagement
public interface RoleUserRepository extends JpaRepository<RoleUserEntity, Long> {

    Set<RoleUserEntity> findAllByUserEntityLogin(String userLogin);
    Optional<RoleUserEntity> findByRoleAndUserEntityLogin(SystemRole role, String userLogin);
    void deleteAllByUserEntityLogin(String userLogin);
}
