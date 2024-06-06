package com.example.frontend.Activity.Recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.frontend.Activity.Detail.DeviceDetailActivity;
import com.example.frontend.Activity.Detail.GameDetailActivity;
import com.example.frontend.Adapter.DeviceAdapter;
import com.example.frontend.Data.Devices;
import com.example.frontend.R;
import com.example.frontend.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DeviceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fetchDevices();
    }
    private void fetchDevices() {
        Call<List<Devices>> call = RetrofitBuilder.INSTANCE.getApi().getDevices();
        call.enqueue(new Callback<List<Devices>>() {
            @Override
            public void onResponse(Call<List<Devices>> call, Response<List<Devices>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DeviceActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Devices> devices = response.body();
                adapter = new DeviceAdapter(devices);
                adapter.setOnItemClickListener(new DeviceAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int deviceId) {
                        Intent intent = new Intent(DeviceActivity.this, DeviceDetailActivity.class);
                        intent.putExtra("ID", deviceId+1);
                        startActivity(intent);
                    }
                });
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Devices>> call, Throwable t) {
                Toast.makeText(DeviceActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
