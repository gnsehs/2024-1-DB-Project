package me.handohun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.handohun.springbootdeveloper.domain.Article;
import me.handohun.springbootdeveloper.domain.Comment;
import me.handohun.springbootdeveloper.dto.*;
import me.handohun.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;



/* 컨트롤러 메서드에는 URL 매핑 GET.. */
@RequiredArgsConstructor
@RestController // HTTP 응답으로 Response Body에 의해 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles") // HTTP 메서드가 Post일때 전달받은 URL과 동일하면 메서드로 매핑
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request, Principal principal) { // RequestBody로 요청 본문값 매핑
        Article savedArticle = blogService.save(request,principal.getName());
        ArticleResponse tempResponse = new ArticleResponse(savedArticle);
        return ResponseEntity.status(HttpStatus.CREATED).body(tempResponse); // 201 created
    }

    /*
    RequestBody는 HTTP를 요청할때 응답에 해당하는 값을 RequestBody 애너테이션이 붙은 대상 객체인 AddArticleRequest에
    매핑
    @RequestBody
    HTTP Request Message Body를 통해 전달된 데이터를 지정한 객체로 역직렬화하는 애노테이션
     */

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream() // 스트림은 여러 데이터가 모여 있는 컬렉션을 간편하게 처리하기 위한 자바 8 에서 추가된 기능
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/article")
    public ResponseEntity<List<ArticleResponse>> findAllArticlesByGame(@RequestParam("game_id") Long game_id) {
        List<ArticleResponse> articles = blogService.findAllByGameId(game_id)
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }


    @GetMapping("/api/articles/{id}") // 이름이 파라미터 이름과 같다면 자동 매칭
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }



    /*
    @PathVariable URL 에서 값을 가져오는 애너테이션
     */

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id
            , @RequestBody UpdateArticleRequest request) {

        Article updatedArticle = blogService.update(id,request);

        return ResponseEntity.ok().body(new ArticleResponse(updatedArticle));
    }
    // crud

    @PostMapping("/api/comments") // request에 article아이디 넣어서 그 article에 댓글 달기
    public ResponseEntity<AddCommentResponse> addComment (@RequestBody AddCommentRequest request, Principal principal) {
        Comment savedComment = blogService.addComment(request,principal.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(new AddCommentResponse(savedComment));
    }

    @GetMapping("api/comments/{id}")
    public ResponseEntity<List<AddCommentResponse>> getCommentsByArticleId(@PathVariable Long id) {
        List<AddCommentResponse> commentResponses = blogService.findCommentsByArticleId(id)
                .stream()
                .map(AddCommentResponse::new)
                .toList();

        return ResponseEntity.ok().body(commentResponses);
    }



}
