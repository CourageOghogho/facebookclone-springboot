package dev.decagon.facebookcloneapp.util;


import dev.decagon.facebookcloneapp.dto.*;
import dev.decagon.facebookcloneapp.model.Comment;
import dev.decagon.facebookcloneapp.model.Login;
import dev.decagon.facebookcloneapp.model.Post;
import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.repositories.CommentRepository;
import dev.decagon.facebookcloneapp.repositories.PostRepository;
import dev.decagon.facebookcloneapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class Mapper {

    private   CommentRepository commentRepository;

    private  UserRepository userRepository;

    private   PostRepository postRepository;


    @Autowired
    public Mapper(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }




    public Login loginDTOtoLoginMapper(LoginDTO loginDTO){
        return Login.builder()
                .email(loginDTO.getEmail())
                .password(loginDTO.getPassword())
                .userId(loginDTO.getUserId())
                .build();
    }

    public List<CommentDTO> commentMapping(List<Comment> comments){
        return comments.stream()
                .map((c -> {
                    return CommentDTO.builder()
                            .userId(c.getUserId())
                            .postId(c.getPostId())
                            .textBody(c.getTextBody())
                            .likes(c.getLikes())
                            .commentId(c.getCommentId())
                            .userName(userRepository.findById(c.getUserId()).get().getName())
                            .build();

                }))
                .collect(Collectors.toList());
    }

    public List<PostResponse> postToViewPostMapper(List<Post> posts){
        List<PostResponse>responses= posts.stream()
                .map((post -> {
                    return PostResponse.builder()
                            .id(post.getId())
                            .userId(post.getUserId())
                            .userName(post.getUserName())
                            .textBody(post.getTextBody())
                            .likes(post.getLikes())
                            .comments(commentToCommentDTPMapper(commentRepository.findByPostId(post.getId()))).build();

                }))
                .collect(Collectors.toList());
        responses.sort((res1,res2)->res2.getId()-res1.getId());
        return responses;

    }

    public List<CommentDTO> commentToCommentDTPMapper(List<Comment> comments) {
        List<CommentDTO> commentDTOS=comments.stream()
                .map(comment ->{
                    return CommentDTO.builder()
                        .userId(comment.getUserId())
                        .commentId(comment.getCommentId())
                        .textBody(comment.getTextBody())
                        .likes(comment.getLikes())
                        .postId(comment.getPostId())
                        .userName(userRepository.findById(comment.getUserId()).get().getName())
                        .build();}
                )
                .collect(Collectors.toList());
        commentDTOS.sort((cm1,cm2)-> cm2.getCommentId()- cm1.getCommentId());
        return commentDTOS;
    }

    public CommentDTO commentToCommentDTOMapper(Comment comment){
        return CommentDTO.builder()
                .userId(comment.getUserId())
                .commentId(comment.getCommentId())
                .textBody(comment.getTextBody())
                .likes(comment.getLikes())
                .postId(comment.getPostId())
                .userName(userRepository.findById(comment.getUserId()).get().getName())
                .build();
    }

    public PostResponse postToViewPostMapper(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .userName(post.getUserName())
                .textBody(post.getTextBody())
                .likes(post.getLikes())
                .comments(commentToCommentDTPMapper(commentRepository.findByPostId(post.getId())))
                .build();
    }
    public User userDTOtoUserMapper(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .gender(userDTO.getGender())
                .build();
    }

    public UserDTO userToUserDto(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGender())
                .build();
    }

    public Comment commentMapper(CommentDTO commentDTO) {
        return Comment.builder()
                .likes(commentDTO.getLikes())
                .postId(commentDTO.getPostId())
                .userId(commentDTO.getUserId())
                .textBody(commentDTO.getTextBody())
                .build();
    }

    public Post postDtoToPost(PostDTO postDTO) {
        return Post.builder()
                .userName(postDTO.getUserName())
                .userId(postDTO.getUserId())
                .textBody(postDTO.getTextBody())
                .likes(postDTO.getLikes())
                .build();
    }

    public PostDTO postToPostDto(Post post) {
        return PostDTO.builder()
                .userName(post.getUserName())
                .id(post.getId())
                .userId(post.getUserId())
                .textBody(post.getTextBody())
                .likes(post.getLikes())
                .build();
    }
}
