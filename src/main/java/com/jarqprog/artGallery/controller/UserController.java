package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.dto.UserDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public interface UserController {

    List<UserDTO> getAllUsers();
    UserDTO findUserById(@PathVariable("id") long id);
    UserDTO addUser(@RequestBody UserDTO userDTO);
    UserDTO updateUser(@PathVariable("id") long id, @RequestBody UserDTO userDTO);
    void removeUser(@PathVariable("id") long id);
}
