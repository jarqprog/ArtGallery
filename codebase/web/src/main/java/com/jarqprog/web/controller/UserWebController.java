package com.jarqprog.web.controller;


import com.jarqprog.domainperson.model.contact.ContactData;
import com.jarqprog.web.AppInitializer;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserWebController {

    private static final Logger logger = LoggerFactory.getLogger(UserWebController.class);

//    @NonNull private final UserService userService;
//
//    @Autowired
//    public UserWebController(@NonNull UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/user-info")
    public String showUserInfo(@NonNull Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String login = authentication.getName();
//        UserFat user = userService.findUserByLogin(login, UserFat.class);
        var user = AppInitializer.getDummyUser();
        ContactData contactData = user.getContact();
        model.addAttribute("login", user.getLogin());
        model.addAttribute("email", contactData.getEmail());
        model.addAttribute("firstName", contactData.getFirstName());
        model.addAttribute("lastName", contactData.getLastName());
        return "/user/user-info";
    }
}
