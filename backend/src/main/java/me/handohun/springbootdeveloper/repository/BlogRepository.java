package me.handohun.springbootdeveloper.repository;

import me.handohun.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속받을때 엔티티와 PK 타입 넣어줘야함
public interface BlogRepository extends JpaRepository<Article, Long> {
}
