package com.jarqprog.artGallery.api.dataLogic.repositories;

import com.jarqprog.artGallery.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Repository
@EnableTransactionManagement
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);
    Optional<User> findOneByLogin(String login);
    Optional<User> findUserByContactId(long contactId);
}
