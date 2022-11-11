package dev.decagon.facebookcloneapp.controllers;

import dev.decagon.facebookcloneapp.dto.PostDTO;
import dev.decagon.facebookcloneapp.model.Post;
import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.services.PostService;
import dev.decagon.facebookcloneapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public  String createPost(HttpSession session, @RequestParam("userid") Integer userId,
                              @RequestParam("textbody") String textBody){
        User user=userService.get(userId);
        postService.save(PostDTO.builder()
                .userId(userId)
                .textBody(textBody)
                .userName(user.getName())
                .likes(0)
                .build()
        );

        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public  String delete(@RequestParam("id") Integer id, HttpSession session){
        Post post=postService.get(id);
        User user=(User) session.getAttribute("user");
        if(user.getId()== post.getUserId()){
            postService.delete(id);
        }
        return "redirect:/home";
    }



}
