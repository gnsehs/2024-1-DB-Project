package me.handohun.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.handohun.springbootdeveloper.domain.Comment;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCommentResponse {
    private Long comment_id;
    private String content;

    public AddCommentResponse(Comment comment) {
        this.comment_id = comment.getId();
        this.content = comment.getContent();
    }
}
