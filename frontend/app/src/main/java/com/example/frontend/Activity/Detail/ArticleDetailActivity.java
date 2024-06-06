package com.example.frontend.Activity.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.Activity.Detail.GameDetailActivity;
import com.example.frontend.Data.Article;
import com.example.frontend.Data.comment_write;
import com.example.frontend.Data.comments;
import com.example.frontend.R;
import com.example.frontend.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDetailActivity extends AppCompatActivity {
    private TextView articleTitle;
    private TextView articleContent;
    private TextView articleGameName;
    private LinearLayout commentsLayout;
    private EditText commentEditText;
    private Button commentbutton;

    private Button reloadbutton;

    private int num;

    private TextView articleAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detail);

        articleTitle = findViewById(R.id.articleTitle);
        articleContent = findViewById(R.id.articleContent);
        articleGameName = findViewById(R.id.articleGameName);
        commentsLayout = findViewById(R.id.commentsLayout);
        articleAuthor = findViewById(R.id.articleAuthor);

        commentEditText = findViewById(R.id.commentEditText);
        commentbutton = findViewById(R.id.commentbutton);

        reloadbutton = findViewById(R.id.reloadbutton);

        // 인텐트로부터 게시물 ID를 받아옴
        int articleId = getIntent().getIntExtra("ID", -1);

        fetchArticleDetails(articleId);

        reloadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchArticleDetails(articleId);
            }
        });

        articleGameName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticleDetailActivity.this, GameDetailActivity.class);
                intent.putExtra("ID", num);
                startActivity(intent);
            }
        });

        // 버튼 클릭 시 댓글 작성
        commentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = commentEditText.getText().toString();
                if (!commentContent.isEmpty()) {
                    postComment(articleId, commentContent);
                } else {
                    Toast.makeText(ArticleDetailActivity.this, "댓글을 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchArticleDetails(int articleId) {
        Call<Article> call = RetrofitBuilder.INSTANCE.getApi().getArticleById(articleId);

        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Article article = response.body();
                    updateUI(article);
                } else {
                    Toast.makeText(ArticleDetailActivity.this, "게시물 정보를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Toast.makeText(ArticleDetailActivity.this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(Article article) {
        articleTitle.setText(article.getTitle());
        articleContent.setText(article.getContent());
        articleGameName.setText("게임 이름: " + article.getGame_name());
        articleAuthor.setText("작성자: " + article.getAuthor());
        num = article.getGame_id();

        // comment를 추가
        for (comments comment : article.getComments()) {
            TextView commentTextView = new TextView(ArticleDetailActivity.this);
            commentTextView.setText(comment.getContent());
            commentsLayout.addView(commentTextView);
        }
    }

    private void postComment(int articleId, String content) {
        comment_write comment = new comment_write(articleId, content);
        Call<Void> call = RetrofitBuilder.INSTANCE.getApi().postComment(comment);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ArticleDetailActivity.this, "댓글이 작성되었습니다.", Toast.LENGTH_SHORT).show();
                    // 작성한 댓글을 화면에 추가하고 싶다면 여기에 추가하는 코드 작성
                } else {
                    Toast.makeText(ArticleDetailActivity.this, "댓글 작성에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ArticleDetailActivity.this, "댓글 작성에 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
