package me.handohun.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.handohun.springbootdeveloper.domain.Comment;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCommentResponse {
    private Long article_id;
    private String author;
    private String content;

    public AddCommentResponse(Comment comment) {
        this.article_id = comment.getArticle().getId();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
    }
}
