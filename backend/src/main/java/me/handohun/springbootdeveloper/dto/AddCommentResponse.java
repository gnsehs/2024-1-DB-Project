package me.handohun.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.handohun.springbootdeveloper.domain.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCommentResponse {
    private Long article_id;
    private String author;
    private String content;
    private String post_time;

    public AddCommentResponse(Comment comment) {
        this.article_id = comment.getArticle().getId();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
        this.post_time = comment.getPostTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
