package com.jarqprog.artGallery.domain.useCases;

import com.jarqprog.artGallery.domain.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO findUserById(long id);
    UserDTO findUserByLogin(String login);
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(long id, UserDTO userDTO);
    void removeUser(long id);
}
