package com.jarqprog.artGallery.rest.controller;

import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.useCases.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class SimpleUserController {

    @Autowired
    UserService userService;

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
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
