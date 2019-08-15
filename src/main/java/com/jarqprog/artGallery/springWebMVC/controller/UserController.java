package com.jarqprog.artGallery.springWebMVC.controller;


import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.useCases.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired UserService userService;

    @GetMapping("/user/user-info")
    public String userInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        UserDTO userDTO = userService.findUserByLogin(login);
        ContactDTO contactDTO = userDTO.getContact();
        model.addAttribute("login", userDTO.getLogin());
        model.addAttribute("email", contactDTO.getEmail());
        model.addAttribute("firstName", contactDTO.getFirstName());
        model.addAttribute("lastName", contactDTO.getLastName());
        model.addAttribute("nickname", contactDTO.getNickname());
        return "/user/user-info";
    }

//    @GetMapping("/showViewPage")
//    public String passParametersWithModel(Model model) {
//        Map<String, String> map = new HashMap<>();
//        map.put("spring", "mvc");
//        model.addAttribute("message", "Baeldung");
//        model.mergeAttributes(map);
//        return "viewPage";
//    }

}
