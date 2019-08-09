package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
@EnableTransactionManagement
public interface RoleRepository extends JpaRepository<Role, Long> {
}
