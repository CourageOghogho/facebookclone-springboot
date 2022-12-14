package dev.decagon.facebookcloneapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private  Integer id;
    private  Integer likes;
    private  String textBody;
    private  String userName;
    private Integer userId;
    private List<CommentDTO> comments;
}
