package me.handohun.springbootdeveloper.dto;

import lombok.Getter;
import me.handohun.springbootdeveloper.domain.Article;
import me.handohun.springbootdeveloper.domain.Comment;

import java.util.List;

@Getter
public class ArticleResponse {
    private final String title;
    private final String content;
    private final String game_name;
    private final String author;
    private final List<AddCommentResponse> comments;

    public ArticleResponse(Article article) { // 엔티티를 인수로 받는 생성자
        this.author = article.getAuthor();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.game_name = article.getGame().getGame_name();
        if (article.getComments() == null) {
            this.comments =null;
        }
        else {
            this.comments = article.getComments().stream().map(AddCommentResponse::new).toList();
        }
    }
}
