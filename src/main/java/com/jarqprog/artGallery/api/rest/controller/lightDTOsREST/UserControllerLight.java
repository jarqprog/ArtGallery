package com.jarqprog.artGallery.api.rest.controller.lightDTOsREST;

import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.domain.dto.lightDto.UserDTOLight;
import com.jarqprog.artGallery.api.dataLogic.useCases.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/light/users")
public class UserControllerLight {

    @NonNull private final UserService userService;
    @NonNull private final DtoConverter dtoConverter;

    @Autowired
    public UserControllerLight(@NonNull UserService userService, @NonNull DtoConverter dtoConverter) {
        this.userService = userService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public List<UserDTOLight> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(h -> dtoConverter.convertHeavyToLight(h, UserDTOLight.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTOLight findUserById(@PathVariable("id") long id) {
        return dtoConverter.convertHeavyToLight(userService.findUserById(id), UserDTOLight.class);
    }

    @PostMapping
    public UserDTOLight addUser(@RequestBody UserDTOLight userDTO) {
        return dtoConverter.convertHeavyToLight(userService.addUser(userDTO), UserDTOLight.class);
    }

    @PutMapping("/{id}")
    public UserDTOLight updateUser(@PathVariable("id") long id, @RequestBody UserDTOLight userDTO) {
        return dtoConverter.convertHeavyToLight(userService.updateUser(id, userDTO), UserDTOLight.class);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
    }
}
