package com.jarqprog.artGallery.api.domains.personal.user;

import com.jarqprog.artGallery.domain.personal.SystemRole;
import com.jarqprog.artGallery.domain.personal.User;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    List<User> getAllUsers();
    <U extends User> List<U> getAllUsers(Class<U> clazz);
    User findUserById(long id);
    <U extends User> U findUserById(long id, Class<U> clazz);
    User findUserByLogin(String login);
    <U extends User> U findUserByLogin(String login, Class<U> clazz);
    long addUser(@NonNull User user);
    long createUserWithRole(@NonNull User user, @NonNull SystemRole role);
    void updateUser(long id, @NonNull User user);
    void removeUser(long id);
}
