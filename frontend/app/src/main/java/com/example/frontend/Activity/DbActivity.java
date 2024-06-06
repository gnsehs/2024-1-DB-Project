package com.example.frontend.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.frontend.Data.Devices;
import com.example.frontend.Data.Gamecompanys;
import com.example.frontend.Data.Games;
import com.example.frontend.JsonPlaceHolderApi;
import com.example.frontend.R;
import com.example.frontend.RetrofitBuilder;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DbActivity extends AppCompatActivity {
    private TextView textViewResult;
    private Button GameButton;
    private Button CompanyButton;
    private Button DeviceButton;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db);

        textViewResult = findViewById(R.id.text_view_result);
        GameButton = findViewById(R.id.gamebutton);
        CompanyButton = findViewById(R.id.companybutton);
        DeviceButton = findViewById(R.id.devicebutton);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        GameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchGames();
            }
        });

        CompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchGameCompanies();
            }
        });

        DeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDevices();
            }
        });
    }

    private void fetchGames() {
        Call<List<Games>> callgame = RetrofitBuilder.INSTANCE.getApi().getGames();
        callgame.enqueue(new Callback<List<Games>>() {
            @Override
            public void onResponse(Call<List<Games>> callgame, Response<List<Games>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Games> gamesList = response.body();
                textViewResult.setText(""); // Clear previous content
                for (Games game : gamesList) {
                    String content = "";
                    content += "이름: " + game.getGame_name() + "\n";
                    content += "출시일: " + game.getRelease_date() + "\n";
                    content += "제조회사: " + game.getMade_by() + "\n";
                    content += "연령제한: " + game.getAge_rating() + "\n";
                    content += "독점작 여부: " + game.getExclusive() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Games>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void fetchGameCompanies() {
        Call<List<Gamecompanys>> callcompany = RetrofitBuilder.INSTANCE.getApi().getGameCompanies();
        callcompany.enqueue(new Callback<List<Gamecompanys>>() {
            @Override
            public void onResponse(Call<List<Gamecompanys>> callcompany, Response<List<Gamecompanys>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Gamecompanys> gameCompanies = response.body();
                textViewResult.setText(""); // Clear previous content
                for (Gamecompanys company : gameCompanies) {
                    String content = "";
                    content += "이름: " + company.getName() + "\n";
                    content += "국가: " + company.getNation() + "\n";
                    content += "설립일: " + company.getFounding_date() + "\n";
                    content += "CEO: " + company.getCeo() + "\n";
                    content += "만든 게임 수: " + company.getNumGames() + "\n\n";
                    textViewResult.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<Gamecompanys>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void fetchDevices() {
        Call<List<Devices>> calldevice = RetrofitBuilder.INSTANCE.getApi().getDevices();
        calldevice.enqueue(new Callback<List<Devices>>() {
            @Override
            public void onResponse(Call<List<Devices>> callgame, Response<List<Devices>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Devices> devicesList = response.body();
                textViewResult.setText(""); // Clear previous content
                for (Devices device : devicesList) {
                    String content = "";
                    content += "이름: " + device.getDevice_name() + "\n";
                    content += "출시일: " + device.getRelease_date() + "\n";
                    content += "제조사: " + device.getMade_by() + "\n";
                    content += "호환 게임 수: " + device.getNum_game() + "\n";
                    content += "독점작 수: " + device.getExclusive_game_num() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Devices>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
