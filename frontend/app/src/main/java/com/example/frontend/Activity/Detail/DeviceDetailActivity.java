package com.example.frontend.Activity.Detail;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.Data.Devices;
import com.example.frontend.R;
import com.example.frontend.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceDetailActivity extends AppCompatActivity {
    private TextView deviceName;
    private TextView deviceMadeBy;
    private TextView deviceReleaseDate;
    private TextView deviceNumGame;
    private TextView deviceExclusiveGameNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_detail);

        deviceName = findViewById(R.id.deviceName);
        deviceMadeBy = findViewById(R.id.deviceMadeBy);
        deviceReleaseDate = findViewById(R.id.deviceReleaseDate);
        deviceNumGame = findViewById(R.id.deviceNumGame);
        deviceExclusiveGameNum = findViewById(R.id.deviceExclusiveGameNum);

        // 인텐트로부터 아이템 ID를 받아옴
        int deviceId = getIntent().getIntExtra("ID", -1);

        fetchDeviceDetails(deviceId);
    }

    private void fetchDeviceDetails(int deviceId) {
        Call<Devices> call = RetrofitBuilder.INSTANCE.getApi().getDeviceById(deviceId);

        call.enqueue(new Callback<Devices>() {
            @Override
            public void onResponse(Call<Devices> call, Response<Devices> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Devices device = response.body();
                    updateUI(device);
                } else {
                    Toast.makeText(DeviceDetailActivity.this, "기기 정보를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Devices> call, Throwable t) {
                Toast.makeText(DeviceDetailActivity.this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(Devices device) {
        deviceName.setText(device.getDevice_name());
        deviceMadeBy.setText("제조사: " + device.getMade_by());
        deviceReleaseDate.setText("출시일: " + device.getRelease_date());
        deviceNumGame.setText("게임 수: " + device.getNum_game());
        deviceExclusiveGameNum.setText("독점 게임 수: " + device.getExclusive_game_num());
    }
}
