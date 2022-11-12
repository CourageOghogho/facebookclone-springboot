package dev.decagon.facebookcloneapp.services;

import dev.decagon.facebookcloneapp.dto.CommentDTO;
import dev.decagon.facebookcloneapp.model.Comment;
import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.repositories.CommentRepository;
import dev.decagon.facebookcloneapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    final private CommentRepository commentRepository;
    @Autowired
    private Mapper mapperService;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO){
        Comment comment=commentRepository.save(mapperService.commentMapper(commentDTO));
        commentDTO.setCommentId(comment.getCommentId());
        return  commentDTO;
    }
    @Override
    public List<User> likedAComment(Integer commentId) {return null;}

    @Override
    public Boolean isLikedAComment(Integer userId, Integer commentId) {
        return null;
    }

    @Override
    public void like(Integer commentId) {
        Comment comment=commentRepository.findById(commentId).orElse(null);
        if(comment!=null)
            comment.setLikes(comment.getLikes()+1);
        commentRepository.saveAndFlush(comment);
    }

    @Override
    public void unlike(Integer userId, Integer commentId) {

    }

    @Override
    public List<Comment> getCommentsByPostId(Integer postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public  List<Comment> getAll(){
        return commentRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }
}
