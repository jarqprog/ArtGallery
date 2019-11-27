package com.jarqprog.personapi.rpc.login;

import com.jarqprog.commonapi.constants.ApiConstants;
import com.jarqprog.personapi.readuser.ApiReadUserDTO;
import com.jarqprog.personapi.readuser.ReadUserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiConstants.BASE_URL_PATH + "login")
public class UserLoginController {

    @NonNull private final ReadUserService readUserService;

    @Autowired
    public UserLoginController(@NonNull ReadUserService readUserService) {
        this.readUserService = readUserService;
    }

    @PostMapping()
    public ApiReadUserDTO login(@RequestBody UserLoginDTOImpl userLogin) {
        return readUserService.getReadUserByLogin(userLogin);
    }
}
