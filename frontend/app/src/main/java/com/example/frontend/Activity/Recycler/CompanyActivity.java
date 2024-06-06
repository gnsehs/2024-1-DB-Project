package com.example.frontend.Activity.Recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.frontend.Activity.Detail.CompanyDetailActivity;
import com.example.frontend.Adapter.CompanyAdapter;
import com.example.frontend.Data.Gamecompanys;
import com.example.frontend.R;
import com.example.frontend.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CompanyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fetchGameCompanies();
    }

    private void fetchGameCompanies() {
        Call<List<Gamecompanys>> call = RetrofitBuilder.INSTANCE.getApi().getGameCompanies();
        call.enqueue(new Callback<List<Gamecompanys>>() {
            @Override
            public void onResponse(Call<List<Gamecompanys>> call, Response<List<Gamecompanys>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(CompanyActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Gamecompanys> companies = response.body();
                adapter = new CompanyAdapter(companies);
                adapter.setOnItemClickListener(new CompanyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int companyId) {
                        Intent intent = new Intent(CompanyActivity.this, CompanyDetailActivity.class);
                        intent.putExtra("ID", companyId+1);
                        startActivity(intent);
                    }
                });
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Gamecompanys>> call, Throwable t) {
                Toast.makeText(CompanyActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
