package com.jarqprog.artGallery.rest.controller.heavyDTOsREST;

import com.jarqprog.artGallery.domain.dto.heavyDto.UserDTO;
import com.jarqprog.artGallery.domain.dto.lightDto.UserDTOLight;
import com.jarqprog.artGallery.springData.useCases.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heavy/users")
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
    public UserDTO addUser(@RequestBody UserDTOLight userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") long id, @RequestBody UserDTOLight userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
    }
}
