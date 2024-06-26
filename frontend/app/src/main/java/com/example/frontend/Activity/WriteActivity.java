package com.example.frontend.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.frontend.Data.Article;
import com.example.frontend.Data.Games;
import com.example.frontend.Data.Write;
import com.example.frontend.R;
import com.example.frontend.RetrofitBuilder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteActivity extends AppCompatActivity {
    private List<Games> gameList;
    private ArrayAdapter<Games> gameAdapter;
    private final String TAG = "ArticleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);

        EditText titleEditText = findViewById(R.id.title);
        EditText contentEditText = findViewById(R.id.content);
        Spinner gameSpinner = findViewById(R.id.gamespinner);
        Button postButton = findViewById(R.id.post);

        fetchGames();

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();

                if (title.isEmpty() || content.isEmpty()) {
                    Toast.makeText(WriteActivity.this, "모든 필드를 채워주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Games selectedGame = (Games) gameSpinner.getSelectedItem();
                int gameId = selectedGame.getGame_id();

                try {
                    Write article = new Write(title, content, gameId);
                    sendPostRequest(article);
                } catch (NumberFormatException e) {
                    Toast.makeText(WriteActivity.this, "유효한 Game ID를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchGames() {

        Call<List<Games>> call = RetrofitBuilder.INSTANCE.getApi().getGames();

        call.enqueue(new Callback<List<Games>>() {
            @Override
            public void onResponse(Call<List<Games>> call, Response<List<Games>> response) {
                if (response.isSuccessful()) {
                    gameList = response.body();
                    if (gameList != null) {
                        gameAdapter = new ArrayAdapter<>(WriteActivity.this, android.R.layout.simple_spinner_item, gameList);
                        gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        ((Spinner) findViewById(R.id.gamespinner)).setAdapter(gameAdapter);
                    }
                } else {
                    Log.d(TAG, "Response not successful: " + response.code());
                    Toast.makeText(WriteActivity.this, "Failed to load games", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Games>> call, Throwable t) {
                Log.e(TAG, "onFailure called", t);
                Toast.makeText(WriteActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendPostRequest(Write article) {
        Call<Void> call = RetrofitBuilder.INSTANCE.getApi().postArticle(article);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "sendPostRequest onResponse called");
                if (response.isSuccessful()) {
                    Toast.makeText(WriteActivity.this, "Article posted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "sendPostRequest Response not successful: " + response.code());
                    Toast.makeText(WriteActivity.this, "Failed to post article", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "sendPostRequest onFailure called", t);
                Toast.makeText(WriteActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
