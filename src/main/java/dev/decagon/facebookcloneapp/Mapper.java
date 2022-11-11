package dev.decagon.facebookcloneapp;


import dev.decagon.facebookcloneapp.dto.*;
import dev.decagon.facebookcloneapp.model.Comment;
import dev.decagon.facebookcloneapp.model.Login;
import dev.decagon.facebookcloneapp.model.Post;
import dev.decagon.facebookcloneapp.model.User;
import dev.decagon.facebookcloneapp.repositories.CommentRepository;
import dev.decagon.facebookcloneapp.repositories.UserRepository;
import dev.decagon.facebookcloneapp.services.CommentService;
import dev.decagon.facebookcloneapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class Mapper {
    @Autowired
    private  static CommentRepository commentRepository;
    @Autowired
    private static UserRepository userRepository;
    @Autowired
    private  static PostService postService;
    @Autowired
    private  static CommentService commentService;


    public  static Login loginDTOtoLoginMapper(LoginDTO loginDTO){
        return Login.builder()
                .email(loginDTO.getEmail())
                .password(loginDTO.getPassword())
                .userId(loginDTO.getUserId())
                .build();
    }

    public  static List<CommentDTO> commentMapping(List<Comment> comments){
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

    public static List<PostResponse> postToViewPostMapper(List<Post> posts){
        return posts.stream()
                .map((post -> {
                    return PostResponse.builder()
                            .id(post.getId())
                            .userId(post.getUserId())
                            .userName(post.getUserName())
                            .textBody(post.getTextBody())
                            .likes(post.getLikes())
                            .comments(postService.getCommentsPostById(post.getId()))
                            .build();
                }))
                .collect(Collectors.toList());


    }
    public  static PostResponse postToViewPostMapper(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .userName(post.getUserName())
                .textBody(post.getTextBody())
                .likes(post.getLikes())
                .comments(commentService.getCommentsByPostId(post.getId()))
                .build();
    }
    public  static User userDTOtoUserMapper(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .gender(userDTO.getGender())
                .build();
    }

    public static UserDTO userToUserDto(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGender())
                .build();
    }

    public static Comment commentMapper(CommentDTO commentDTO) {
        return Comment.builder()
                .likes(commentDTO.getLikes())
                .postId(commentDTO.getPostId())
                .userId(commentDTO.getUserId())
                .textBody(commentDTO.getTextBody())
                .build();
    }

    public static Post postDtoToPost(PostDTO postDTO) {
        return Post.builder()
                .userName(postDTO.getUserName())
                .userId(postDTO.getUserId())
                .textBody(postDTO.getTextBody())
                .likes(postDTO.getLikes())
                .build();
    }

    public static PostDTO postToPostDto(Post post) {
        return PostDTO.builder()
                .userName(post.getUserName())
                .id(post.getId())
                .userId(post.getUserId())
                .textBody(post.getTextBody())
                .likes(post.getLikes())
                .build();
    }
}
