package com.jarqprog.artGallery.api.domains.personal.user;

import com.jarqprog.artGallery.domain.personal.SystemRole;
import com.jarqprog.artGallery.domain.personal.UserData;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    List<UserData> getAllUsers();
    <U extends UserData> List<U> getAllUsers(Class<U> clazz);
    UserData findUserById(long id);
    <U extends UserData> U findUserById(long id, Class<U> clazz);
    UserData findUserByLogin(String login);
    <U extends UserData> U findUserByLogin(String login, Class<U> clazz);
    long addUser(@NonNull UserData userData);
    long createUserWithRole(@NonNull UserData userData, @NonNull SystemRole role);
    void updateUser(long id, @NonNull UserData userData);
    void removeUser(long id);
}
