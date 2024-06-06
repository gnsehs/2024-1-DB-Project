package com.example.frontend.Activity.Detail;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.Data.Gamecompanys;
import com.example.frontend.R;
import com.example.frontend.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDetailActivity extends AppCompatActivity {
    private TextView companyName;
    private TextView companyNation;
    private TextView companyFoundingDate;
    private TextView companyCEO;
    private TextView companyNumGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_detail);

        companyName = findViewById(R.id.companyName);
        companyNation = findViewById(R.id.companyNation);
        companyFoundingDate = findViewById(R.id.companyFoundingDate);
        companyCEO = findViewById(R.id.companyCEO);
        companyNumGames = findViewById(R.id.companyNumGames);

        // 인텐트로부터 아이템 ID를 받아옴
        int companyId = getIntent().getIntExtra("ID", -1);

        fetchCompanyDetails(companyId);
    }

    private void fetchCompanyDetails(int companyId) {
        Call<Gamecompanys> call = RetrofitBuilder.INSTANCE.getApi().getCompanyById(companyId);

        call.enqueue(new Callback<Gamecompanys>() {
            @Override
            public void onResponse(Call<Gamecompanys> call, Response<Gamecompanys> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Gamecompanys company = response.body();
                    updateUI(company);
                } else {
                    Toast.makeText(CompanyDetailActivity.this, "회사 정보를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Gamecompanys> call, Throwable t) {
                Toast.makeText(CompanyDetailActivity.this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(Gamecompanys company) {
        companyName.setText(company.getName());
        companyNation.setText("국가: " + company.getNation());
        companyFoundingDate.setText("설립일: " + company.getFounding_date());
        companyCEO.setText("CEO: " + company.getCeo());
        companyNumGames.setText("게임 개수: " + company.getNumGames());
    }
}
