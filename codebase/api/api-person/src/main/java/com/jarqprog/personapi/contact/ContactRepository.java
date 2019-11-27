package com.jarqprog.personapi.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Repository
@EnableTransactionManagement
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    Optional<ContactEntity> findOneByEmail(String email);

}
