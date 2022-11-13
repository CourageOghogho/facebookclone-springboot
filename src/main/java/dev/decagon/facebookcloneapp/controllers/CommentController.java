package dev.decagon.facebookcloneapp.controllers;

import dev.decagon.facebookcloneapp.dto.CommentDTO;
import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/new")
    public String createComment(@RequestParam("postid") Integer postId,@RequestParam("textboy") String textBody, HttpSession session){
        if (textBody==null) return "redirect:/home";
        if (textBody.isEmpty()) return "redirect:/home";
        User user=(User) session.getAttribute("user");
        commentService.save(CommentDTO.builder()
                .userId(user.getId())
                .postId(postId)
                .textBody(textBody)
                .userName(user.getName())
                .likes(0)
                .build()
        );

        return "redirect:/home";
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable("id")Integer id){
        commentService.delete(id);
        return "redirect:/comments";
    }

    @GetMapping("/")
    public  ModelAndView allComments(ModelAndView mav){
        mav.addObject("comments",commentService.getAll());
        mav.setViewName("comments");
        return mav;
    }
}
