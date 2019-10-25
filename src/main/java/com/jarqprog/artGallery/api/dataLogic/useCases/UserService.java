package com.jarqprog.artGallery.api.dataLogic.useCases;

import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.dto.fatDTO.UserFat;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO findUserById(long id);
    UserFat findUserByLogin(String login);
    UserDTO addUser(@NonNull UserDTO userDTO);
    UserDTO updateUser(long id, @NonNull UserDTO userDTO);
    void removeUser(long id);
}
