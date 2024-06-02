package me.handohun.springbootdeveloper.dto;

import lombok.Getter;
import me.handohun.springbootdeveloper.domain.Article;

@Getter
public class ArticleResponse {
    private final String title;
    private final String content;
    private final String game_name;

    public ArticleResponse(Article article) { // 엔티티를 인수로 받는 생성자
        this.title = article.getTitle();
        this.content = article.getContent();
        this.game_name = article.getGame().getGame_name();
    }
}
