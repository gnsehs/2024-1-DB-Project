package me.handohun.springbootdeveloper.dto;

import lombok.Getter;
import me.handohun.springbootdeveloper.domain.Article;

@Getter
public class ArticleResponse {
    private final String title;
    private final String content;

    public ArticleResponse(Article article) { // 엔티티를 인수로 받는 생성자
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
