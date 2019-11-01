package com.jarqprog.artGallery.api.domains.personal.user.controller;

import com.jarqprog.artGallery.api.ApiConstants;
import com.jarqprog.artGallery.api.domains.personal.user.UserService;
import com.jarqprog.artGallery.api.domains.personal.user.dto.UserDTO;
import com.jarqprog.artGallery.api.domains.personal.user.dto.UserFat;
import com.jarqprog.artGallery.api.domains.personal.user.dto.UserThin;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.jarqprog.artGallery.api.domains.OutputMode.FAT;

@RestController
@RequestMapping(ApiConstants.BASE_URL_PATH + "users")
public class UserController {

    @NonNull private final UserService userService;

    @Autowired
    public UserController(@NonNull UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<? extends UserDTO> getAllUsers(@RequestParam(required = false, name = "mode") String mode) {
        return userService.getAllUsers(getOutputClass(mode));
    }

    @GetMapping("/{id}")
    public UserDTO findUserById(@RequestParam(required = false, name = "mode") String mode,
                                 @PathVariable("id") long id) {
        return userService.findUserById(id, getOutputClass(mode));
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDTO userDTO) {
        long id = userService.addUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        userService.updateUser(id, userDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return ResponseEntity.accepted().build();
    }

    private Class<? extends UserDTO> getOutputClass(String mode) {
        Class<UserThin> defaultOutput = UserThin.class;
        if (mode == null) {
            return defaultOutput;
        }
        switch (mode) {
            case FAT:
                return UserFat.class;
            default:
                return defaultOutput;
        }
    }
}
