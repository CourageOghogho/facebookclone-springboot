package dev.decagon.facebookcloneapp.services;

import dev.decagon.facebookcloneapp.dto.PostDTO;
import dev.decagon.facebookcloneapp.model.Comment;
import dev.decagon.facebookcloneapp.model.Post;

import java.util.List;

public interface PostService {
    PostDTO save(PostDTO post);
    List<Post>getAllPosts();

    Integer like(Integer postId);

    List<Comment> getCommentsPostById(Integer postId);

    void delete(Integer postId);

    PostDTO edit(Integer postId, String message);

    Post get(Integer postid);
}
