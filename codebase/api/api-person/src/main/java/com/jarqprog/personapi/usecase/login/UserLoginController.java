package com.jarqprog.personapi.usecase.login;

import com.jarqprog.commonapi.constants.ApiConstants;
import com.jarqprog.domainperson.usecase.login.UserLogin;
import com.jarqprog.personapi.read.ReadUserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import readuser.ReadUser;


@RestController
@RequestMapping(ApiConstants.BASE_URL_PATH + "login")
public class UserLoginController {

    @NonNull private final ReadUserService readUserService;

    @Autowired
    public UserLoginController(@NonNull ReadUserService readUserService) {
        this.readUserService = readUserService;
    }

    @PostMapping()
    public ReadUser login(@RequestBody UserLogin userLogin) {
        return readUserService.getReadUserByLogin(userLogin);
    }
}
