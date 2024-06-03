package com.example.frontend.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.frontend.Data.GameCompany;
import com.example.frontend.Data.Games;
import com.example.frontend.JsonPlaceHolderApi;
import com.example.frontend.R;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DbActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Games>> callgame = jsonPlaceHolderApi.getGames();
        callgame.enqueue(new Callback<List<Games>>() {
            @Override
            public void onResponse(Call<List<Games>> callgame, Response<List<Games>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Games> gamesList = response.body();
                for (Games game : gamesList) {
                    String content = "";
                    content += "Name: " + game.getName() + "\n";
                    content += "Date: " + game.getDate() + "\n";
                    content += "Made By: " + game.getMade() + "\n";
                    content += "Age: " + game.getAge() + "\n";
                    content += "Exclusive: " + game.getExclusive() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Games>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        Call<List<GameCompany>> callcompany = jsonPlaceHolderApi.getGameCompanies();
        callcompany.enqueue(new Callback<List<GameCompany>>() {
            @Override
            public void onResponse(Call<List<GameCompany>> callcompany, Response<List<GameCompany>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<GameCompany> gameCompanies = response.body();
                for (GameCompany company : gameCompanies) {
                    String content = "";
                    content += "Name: " + company.getName() + "\n";
                    content += "Nation: " + company.getNation() + "\n";
                    content += "Founding Date: " + company.getDate() + "\n";
                    content += "CEO is: " + company.getCeo() + "\n";
                    content += "Number of Games: " + company.getNum() + "\n\n";
                    textViewResult.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<GameCompany>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
