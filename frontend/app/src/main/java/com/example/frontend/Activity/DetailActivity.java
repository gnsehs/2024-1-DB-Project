package com.example.frontend.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.frontend.R;

public class DetailActivity extends AppCompatActivity {
    private TextView itemNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        itemNameTextView = findViewById(R.id.item_name_text_view);

        // 인텐트로부터 아이템 이름을 받아옴
        int gameId = getIntent().getIntExtra("GAME_ID", -1);
        itemNameTextView.setText(String.valueOf(gameId));
    }
}
