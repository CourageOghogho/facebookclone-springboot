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
    public ModelAndView createComment(@RequestParam("texboy") String textBody, HttpSession session){
        ModelAndView mav=new ModelAndView();
        User user=(User) session.getAttribute("user");
        commentService.save(CommentDTO.builder()
                .userId(user.getId())
                .textBody(textBody)
                .userName(user.getName())
                .likes(0)
                .build()
        );
        mav.addObject("comments",commentService.getAll());
        mav.setViewName("comments");
        return mav;
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
