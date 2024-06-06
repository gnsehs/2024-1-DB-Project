package com.example.frontend.Activity.Recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.frontend.Activity.Detail.ArticleDetailActivity;
import com.example.frontend.Activity.Detail.GameDetailActivity;
import com.example.frontend.Adapter.ArticleAdapter;
import com.example.frontend.Data.Article;
import com.example.frontend.R;
import com.example.frontend.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ArticleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Button reloadButton;

    private ArticleAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_check);

        recyclerView = findViewById(R.id.article_view);
        reloadButton = findViewById(R.id.reloadbutton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        reload();

        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });
    }

    private void reload() {
        Call<List<Article>> call = RetrofitBuilder.INSTANCE.getApi().getArticle();
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ArticleActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Article> articles = response.body();
                adapter = new ArticleAdapter(articles);
                adapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int articleId) {
                        Intent intent = new Intent(ArticleActivity.this, ArticleDetailActivity.class);
                        intent.putExtra("ID", articleId+1);
                        startActivity(intent);
                    }
                });
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(ArticleActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
