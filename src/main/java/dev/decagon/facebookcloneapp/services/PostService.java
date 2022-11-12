package dev.decagon.facebookcloneapp.services;

import dev.decagon.facebookcloneapp.dto.PostDTO;
import dev.decagon.facebookcloneapp.model.Comment;
import dev.decagon.facebookcloneapp.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostDTO save(PostDTO post);
    List<Post>getAllPosts();

    Integer like(Integer postId);

    List<Comment> getCommentsPostById(Integer postId);

    void delete(Integer postId);

    PostDTO edit(Integer postId, String message);

    Post get(Integer postid);
}
