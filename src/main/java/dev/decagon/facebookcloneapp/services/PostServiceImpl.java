package dev.decagon.facebookcloneapp.services;

import dev.decagon.facebookcloneapp.dto.PostDTO;
import dev.decagon.facebookcloneapp.model.Comment;
import dev.decagon.facebookcloneapp.model.Post;
import dev.decagon.facebookcloneapp.repositories.CommentRepository;
import dev.decagon.facebookcloneapp.repositories.PostRepository;
import dev.decagon.facebookcloneapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public PostDTO save(PostDTO postDTO) {
        Post post=postRepository.save(Mapper.postDtoToPost(postDTO));
        postDTO.setId(post.getId());
        return postDTO;
    }

    @Override
    public List<Post> getAllPosts() {

        return postRepository.findAll();

    }



    @Override
    public Integer like( Integer postId) {
        Post post=postRepository.findById(postId).get();
        if (post!=null) post.setLikes(post.getLikes()+1);
        return post.getLikes();
    }// this is the like method

    @Override
    public List<Comment> getCommentsPostById(Integer postId) {
        return commentRepository.findByPostId(postId);
    }


    @Override
    public void delete(Integer postId) {
         postRepository.delete(postRepository.findById(postId).get());
    }

    @Override
    public PostDTO edit(Integer postId, String message) {
        Post post=postRepository.findById(postId).get();
        post.setTextBody(message);

        return Mapper.postToPostDto(postRepository.saveAndFlush(post));
    }

    @Override
    public Post get(Integer postid) {
        return postRepository.findById(postid).get();
    }


}

