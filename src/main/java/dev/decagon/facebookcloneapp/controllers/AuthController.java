package dev.decagon.facebookcloneapp.controllers;

import dev.decagon.facebookcloneapp.exeption.LoginException;
import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.services.LoginService;
import dev.decagon.facebookcloneapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/auth")
@SessionAttributes({"userid"})
public class AuthController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private PostService postService;

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes ra,
                        HttpSession session){
       try{ User loggedUser=loginService.login(email, password);
           if(loggedUser!=null){
               session.setAttribute("user",loggedUser);
               System.out.println(loggedUser.getId());
               ra.addFlashAttribute("login",loggedUser);
               return "redirect:/home";
           }else {

           }
       }
       catch (LoginException le){
           return "redirect:/auth/login";
       }
        return "redirect:/auth/login";
    }

    @GetMapping("/logout")
    public  String loginOut(HttpSession session){
        User loggedUser=(User)session.getAttribute("user");
        if(loggedUser!=null)
            session.removeAttribute("user");
        return "login";
    }

    @GetMapping("/login")
    public  String loginPage(){

        return "login";
    }

}
