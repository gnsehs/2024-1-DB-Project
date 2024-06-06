package com.example.frontend.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.frontend.Adapter.CompanyAdapter;
import com.example.frontend.Adapter.DeviceAdapter;
import com.example.frontend.Data.Devices;
import com.example.frontend.Data.Gamecompanys;
import com.example.frontend.Data.Games;
import com.example.frontend.Adapter.GameAdapter;
import com.example.frontend.JsonPlaceHolderApi;
import com.example.frontend.R;
import com.example.frontend.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DbActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button gameButton;
    private Button companyButton;
    private Button deviceButton;

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private GameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db);

        recyclerView = findViewById(R.id.recycler_view);
        gameButton = findViewById(R.id.gamebutton);
        companyButton = findViewById(R.id.companybutton);
        deviceButton = findViewById(R.id.devicebutton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchGames();
            }
        });

        companyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchGameCompanies();
            }
        });

        deviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDevices();
            }
        });
    }

    private void fetchGames() {
        Call<List<Games>> call = RetrofitBuilder.INSTANCE.getApi().getGames();
        call.enqueue(new Callback<List<Games>>() {
            @Override
            public void onResponse(Call<List<Games>> call, Response<List<Games>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DbActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Games> games = response.body();
                adapter = new GameAdapter(games);
                adapter.setOnItemClickListener(new GameAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int gameId) {
                        Intent intent = new Intent(DbActivity.this, DetailActivity.class);
                        intent.putExtra("GAME_ID", gameId+1);
                        startActivity(intent);
                    }
                });
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Games>> call, Throwable t) {
                Toast.makeText(DbActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchGameCompanies() {
        Call<List<Gamecompanys>> call = RetrofitBuilder.INSTANCE.getApi().getGameCompanies();
        call.enqueue(new Callback<List<Gamecompanys>>() {
            @Override
            public void onResponse(Call<List<Gamecompanys>> call, Response<List<Gamecompanys>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DbActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Gamecompanys> companies = response.body();
                //adapter = new CompanyAdapter(companies);
                adapter.setOnItemClickListener(new GameAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int companyId) {
                        Intent intent = new Intent(DbActivity.this, DetailActivity.class);
                        intent.putExtra("GAME_ID", companyId+1);
                        startActivity(intent);
                    }
                });
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Gamecompanys>> call, Throwable t) {
                Toast.makeText(DbActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchDevices() {
        Call<List<Devices>> call = RetrofitBuilder.INSTANCE.getApi().getDevices();
        call.enqueue(new Callback<List<Devices>>() {
            @Override
            public void onResponse(Call<List<Devices>> call, Response<List<Devices>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DbActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Devices> devices = response.body();
                //adapter = new DeviceAdapter(devices);
                adapter.setOnItemClickListener(new GameAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int deviceId) {
                        Intent intent = new Intent(DbActivity.this, DetailActivity.class);
                        intent.putExtra("GAME_ID", deviceId+1);
                        startActivity(intent);
                    }
                });
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Devices>> call, Throwable t) {
                Toast.makeText(DbActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
