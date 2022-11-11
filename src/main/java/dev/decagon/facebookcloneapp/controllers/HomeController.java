package dev.decagon.facebookcloneapp.controllers;

import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.services.PostService;
import dev.decagon.facebookcloneapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller

public class HomeController {
    @Autowired
    private PostService postService;

    @GetMapping("/home")
    public String getHome(Model mav, HttpSession session){
        User loggedUser=(User)session.getAttribute("user");
        session.setAttribute("posts",Mapper.postToViewPostMapper(postService.getAllPosts()));
        System.out.println(loggedUser);


        return "home";
    }
}
