package com.example.frontend


import android.os.Bundle
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val LoginButton: Button = findViewById(R.id.loginbutton)
        val dbButton: Button = findViewById(R.id.dbbutton)

        LoginButton.setOnClickListener {
            // cctvbutton을 클릭했을 때 CctvActivity를 시작합니다.
            var intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        dbButton.setOnClickListener {
            // cctvbutton을 클릭했을 때 CctvActivity를 시작합니다.
            var intent = Intent(this@MainActivity, DbActivity::class.java)
            startActivity(intent)
        }
    }
}