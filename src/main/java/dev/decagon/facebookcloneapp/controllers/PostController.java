package dev.decagon.facebookcloneapp.controllers;

import dev.decagon.facebookcloneapp.dto.PostDTO;
import dev.decagon.facebookcloneapp.model.Post;
import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.services.PostService;
import dev.decagon.facebookcloneapp.services.UserService;
import dev.decagon.facebookcloneapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private Mapper mapperService;



    @PostMapping("/new")
    public  String createPost(HttpSession session, @RequestParam("textbody") String textBody){
        User loggedUser=(User)session.getAttribute("user");
        if(loggedUser==null){
            return "redirect:/login";
        }
        postService.save(PostDTO.builder()
                .userId(loggedUser.getId())
                .textBody(textBody)
                .userName(loggedUser.getName())
                .likes(0)
                .build()
        );
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public  String delete(@RequestParam("id") Integer id, HttpSession session){
        Post post=postService.get(id);
        User user=(User) session.getAttribute("user");
        if(Objects.equals(user.getId(), post.getUserId())){
            postService.delete(id);
        }
        return "redirect:/home";
    }

    @GetMapping("/like/")
    public String likeAPost(@RequestParam("id") Integer id, HttpSession session){
        Post post=postService.get(id);
        User loggeduser=(User)session.getAttribute("user");
        post.setLikes(post.getLikes()+1);
        postService.save(mapperService.postToPostDto(post));
        return "redirect:/home";
    }

    @PostMapping("/edit")
    public String editPost(@RequestParam("textbody") String textBody,@RequestParam("postid") Integer postid, HttpSession session){
        Post post=postService.get(postid);
        User loggedUser=(User)session.getAttribute("user");
        if(Objects.equals(loggedUser.getId(), post.getUserId())){
            post.setTextBody(textBody);
            postService.save(mapperService.postToPostDto(post));
        }
        return "redirect:/home";
    }
    @GetMapping("/edit")
    public ModelAndView editPage(@RequestParam("id") Integer postId){
        ModelAndView mav=new ModelAndView();
        mav.addObject("post",mapperService.postToPostDto(postService.get(postId)));
        mav.setViewName("post_edit");
        return mav;
    }

}
