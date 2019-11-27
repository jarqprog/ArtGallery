package com.jarqprog.personapi.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Repository
@EnableTransactionManagement
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByLogin(String login);
    Optional<UserEntity> findUserByLoginAndPassword(String login, String password);
    Optional<UserEntity> findUserByContactEntityId(long contactEntityId);
}
