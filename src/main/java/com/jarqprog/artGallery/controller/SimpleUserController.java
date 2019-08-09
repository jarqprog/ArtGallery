package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.dto.UserDTO;
import com.jarqprog.artGallery.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class SimpleUserController implements UserController {

    @Autowired
    UserService userService;

    @Override
    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @GetMapping("/{id}")
    public UserDTO findUserById(@PathVariable("id") long id) {
        return userService.findUserById(id);
    }

    @Override
    @PostMapping
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @Override
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
    }
}
