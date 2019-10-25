package com.jarqprog.artGallery.api.dataLogic.repositories;

import com.jarqprog.artGallery.domain.entity.Role;
import com.jarqprog.artGallery.domain.entity.AuthorizationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Repository
@EnableTransactionManagement
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(AuthorizationRole role);
}
