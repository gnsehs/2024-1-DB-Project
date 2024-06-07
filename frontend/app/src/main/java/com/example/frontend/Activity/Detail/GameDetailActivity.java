package com.example.frontend.Activity.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frontend.Activity.Recycler.ArticleActivity;
import com.example.frontend.Adapter.ArticleAdapter;
import com.example.frontend.Data.Article;
import com.example.frontend.Data.Games;
import com.example.frontend.JsonPlaceHolderApi;
import com.example.frontend.R;
import com.example.frontend.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameDetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView date;
    private TextView made;
    private TextView age;
    private TextView exclusive;
    private TextView numDevice;

    private ArticleAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_detail);

        recyclerView = findViewById(R.id.recycler_view);

        title = findViewById(R.id.gametitle);
        date = findViewById(R.id.gamedate);
        made = findViewById(R.id.gamemade);
        age = findViewById(R.id.gameage);
        exclusive = findViewById(R.id.gameexclusive);
        numDevice = findViewById(R.id.gamenumdevice);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // 인텐트로부터 아이템 이름을 받아옴
        int gameId = getIntent().getIntExtra("ID", -1);

        fetchGameDetails(gameId);

        fetchArticle(gameId);
    }
    private void fetchGameDetails(int gameId) {
        Call<Games> call = RetrofitBuilder.INSTANCE.getApi().getGameById(gameId);

        call.enqueue(new Callback<Games>() {
            @Override
            public void onResponse(Call<Games> call, Response<Games> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Games game = response.body();
                    updateUI(game);
                } else {
                    Toast.makeText(GameDetailActivity.this, "Failed to retrieve game details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Games> call, Throwable t) {
                Toast.makeText(GameDetailActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(Games game) {
        title.setText(game.getGame_name());
        date.setText("출시일: " + game.getRelease_date());
        made.setText("제조사: " + game.getMade_by());
        age.setText("연령: " + game.getAge_rating());
        exclusive.setText("독점 여부: " + game.getExclusive());
        numDevice.setText("지원 기기 수: " + game.getNum_device());
    }

    private void fetchArticle(int gameId) {
        Call<List<Article>> call = RetrofitBuilder.INSTANCE.getApi().getOneArticle(gameId);
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(GameDetailActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Article> articles = response.body();
                adapter = new ArticleAdapter(articles);
                adapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int articleId) {
                        Intent intent = new Intent(GameDetailActivity.this, ArticleDetailActivity.class);
                        intent.putExtra("ID", articleId+1);
                        startActivity(intent);
                    }
                });
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(GameDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
