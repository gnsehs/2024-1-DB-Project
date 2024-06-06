package me.handohun.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.domain.Article;
import me.handohun.springbootdeveloper.domain.Comment;
import me.handohun.springbootdeveloper.dto.AddArticleRequest;
import me.handohun.springbootdeveloper.dto.AddCommentRequest;
import me.handohun.springbootdeveloper.dto.UpdateArticleRequest;
import me.handohun.springbootdeveloper.repository.BlogRepository;
import me.handohun.springbootdeveloper.repository.CommentRepository;
import me.handohun.springbootdeveloper.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가 // 빈을 생성자로 생성
@Service // 해당 클래스를 빈으로 서블릿 컨테이너에 등록ㅁ
public class BlogService {
    private final BlogRepository blogRepository;
    private final GameRepository gameRepository;
    private final CommentRepository commentRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request, String userName) {
        Article tempArticle = request.toEntity(userName);
        tempArticle.setGame(gameRepository.findById(request.getGame_id())
                .orElseThrow(() -> new IllegalArgumentException("not game found : " + request.getGame_id())));// article db에 저장
        blogRepository.save(tempArticle); // save()는 JPArepo.. 에서 지원
        return tempArticle;
    }

    // RequiredArgsConstructor 없다면
//    @Autowired
//    public BlogService(BlogRepository blogRepository) {
//        this.blogRepository = blogRepository;
//    }


    public List<Article> findAll() { // 테이블에 저장되어있는 모든 데이터 조회
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public List<Article> findAllByGameId(Long game_id) {
        return blogRepository.findAllByGame_id(game_id);
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    /* 특정 아이디의 글을 수정하는 update */
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+ id));

        article.update(request.getTitle(),request.getContent()); // request dto 를 통해 업데이트 하기

        return article;
    }

    public Comment addComment(AddCommentRequest request, String userName) {
        Article article = blogRepository.findById(request.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("not found " + request.getArticleId()));

        return commentRepository.save(request.toEntity(userName,article));
    }

    public List<Comment> findCommentsByArticleId(Long article_id) {
        Article tempArticle = blogRepository.findById(article_id)
                .orElseThrow(() -> new IllegalArgumentException("not found " + article_id));

        return tempArticle.getComments();
    }

}
