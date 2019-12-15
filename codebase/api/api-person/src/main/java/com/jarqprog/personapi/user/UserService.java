package com.jarqprog.personapi.user;

import com.jarqprog.domainperson.SystemRole;
import com.jarqprog.domainperson.user.UserData;
import com.jarqprog.personapi.user.dto.ApiUserDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    List<ApiUserDTO> getAllUsers();
    <U extends ApiUserDTO> List<U> getAllUsers(Class<U> clazz);
    ApiUserDTO findUserById(long id);
    <U extends ApiUserDTO> U findUserById(long id, Class<U> clazz);
    ApiUserDTO findUserByLogin(String login);
    <U extends ApiUserDTO> U findUserByLogin(String login, Class<U> clazz);
    long addUser(@NonNull UserData userData);
    long createUserWithRole(@NonNull UserData userData, @NonNull SystemRole role);
    void updateUser(long id, @NonNull UserData userData);
    void removeUser(long id);
}
