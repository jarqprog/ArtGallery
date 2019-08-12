package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Role;
import com.jarqprog.artGallery.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Repository
@EnableTransactionManagement
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(Roles role);
}