package dev.decagon.facebookcloneapp.controllers;

import dev.decagon.facebookcloneapp.dto.PostResponse;
import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.services.PostService;
import dev.decagon.facebookcloneapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller

public class HomeController {
    @Autowired
    private PostService postService;
    @Autowired
    private Mapper mapperService;

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView mav, HttpSession session){
        User loggedUser=(User)session.getAttribute("user");
        if(loggedUser==null){
            mav.setViewName("login");

        }else{
            List<PostResponse> postResponse=mapperService.postToViewPostMapper(postService.getAllPosts());
            mav.addObject("posts",postResponse);
            mav.setViewName("home");
        }

        return mav;
    }
    @PostMapping("/login")
    public String redirectToLoginPage(){
        return "redirect:/auth/login";
    }
}
