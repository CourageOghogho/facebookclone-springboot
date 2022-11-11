package dev.decagon.facebookcloneapp.controllers;

import dev.decagon.facebookcloneapp.dto.LoginDTO;
import dev.decagon.facebookcloneapp.dto.RegistrationRequest;
import dev.decagon.facebookcloneapp.dto.UserDTO;
import dev.decagon.facebookcloneapp.enums.Gender;
import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.services.LoginService;
import dev.decagon.facebookcloneapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private  LoginService loginService;
    @Autowired
    private  UserService userService;

    @PostMapping("/register")
    public String register(@ModelAttribute("registrationRequest")RegistrationRequest request){

        User user=userService.save(UserDTO.builder()
                .name(request.getName())
                .email(request.getEmail())
                .gender(Gender.valueOf(request.getGender()))
                .build());

        loginService.save(LoginDTO.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .userId(user.getId()).build());
        return "redirect:/auth/login";
    }

    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute("registrationRequest",new RegistrationRequest());

        return "signup";
    }

}
