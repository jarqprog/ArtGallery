package com.jarqprog.artGallery.service.user;

import com.jarqprog.artGallery.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO findUserById(long id);
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(long id, UserDTO userDTO);
    void removeUser(long id);
}
