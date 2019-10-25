package com.jarqprog.artGallery.api.rest.controller;

import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.api.dataLogic.useCases.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @NonNull private final UserService userService;

    @Autowired
    public UserController(@NonNull UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO findUserById(@PathVariable("id") long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
    }
}
