package com.jarqprog.artGallery.api.dataLogic.useCases;

import com.jarqprog.artGallery.domain.dto.heavyDto.UserDTO;
import com.jarqprog.artGallery.domain.dto.lightDto.UserDTOLight;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO findUserById(long id);
    UserDTO findUserByLogin(String login);
    UserDTO addUser(@NonNull UserDTOLight userDTO);
    UserDTO updateUser(long id, @NonNull UserDTOLight userDTO);
    void removeUser(long id);
}
