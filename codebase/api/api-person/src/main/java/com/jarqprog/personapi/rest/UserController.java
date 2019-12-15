package com.jarqprog.personapi.rest;

import com.jarqprog.commonapi.constants.ApiConstants;
import com.jarqprog.personapi.user.UserService;
import com.jarqprog.personapi.user.dto.ApiUserDTO;
import com.jarqprog.personapi.user.dto.UserFat;
import com.jarqprog.personapi.user.dto.UserThin;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.jarqprog.commonapi.constants.OutputMode.FAT;


@RestController
@RequestMapping(ApiConstants.BASE_URL_PATH + "users")
class UserController {

    @NonNull private final UserService userService;

    @Autowired
    public UserController(@NonNull UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<? extends ApiUserDTO> getAllUsers(@RequestParam(required = false, name = "mode") String mode) {
        return userService.getAllUsers(getOutputClass(mode));
    }

    @GetMapping("/{id}")
    public ApiUserDTO findUserById(@RequestParam(required = false, name = "mode") String mode,
                                   @PathVariable("id") long id) {
        return userService.findUserById(id, getOutputClass(mode));
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody ApiUserDTO userDTO) {
        long id = userService.addUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") long id, @RequestBody ApiUserDTO userDTO) {
        userService.updateUser(id, userDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return ResponseEntity.accepted().build();
    }

    private Class<? extends ApiUserDTO> getOutputClass(String mode) {
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
