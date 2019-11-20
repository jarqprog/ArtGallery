package com.jarqprog.personapi.domains.user;

import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.user.UserData;
import com.jarqprog.personapi.domains.user.dto.UserDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    List<UserDTO> getAllUsers();
    <U extends UserDTO> List<U> getAllUsers(Class<U> clazz);
    UserDTO findUserById(long id);
    <U extends UserDTO> U findUserById(long id, Class<U> clazz);
    UserDTO findUserByLogin(String login);
    <U extends UserDTO> U findUserByLogin(String login, Class<U> clazz);
    long addUser(@NonNull UserData userData);
    long createUserWithRole(@NonNull UserData userData, @NonNull SystemRole role);
    void updateUser(long id, @NonNull UserData userData);
    void removeUser(long id);
}
