    package com.example.frontend.Activity
    
    import android.os.Bundle
    import android.content.Intent
    import android.util.Log
    import android.widget.Button
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import com.example.frontend.Data.Devices
    import com.example.frontend.Data.Gamecompanys
    import com.example.frontend.Data.Games
    import com.example.frontend.R
    import com.example.frontend.RetrofitBuilder
    import retrofit2.Call
    import retrofit2.Callback
    import retrofit2.Response
    
    class MainActivity : AppCompatActivity() {
    
        private lateinit var gamesList: List<Games>
        private lateinit var companyList: List<Gamecompanys>
        private lateinit var deviceList: List<Devices>
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val signupButton: Button = findViewById(R.id.signupbutton)
            val articleButton: Button = findViewById(R.id.articlebutton)
            val dbButton: Button = findViewById(R.id.dbbutton)

            dbButton.setOnClickListener {
                val intent = Intent(this@MainActivity, DbActivity::class.java)
                startActivity(intent)
            }
    
            signupButton.setOnClickListener {
                val intent = Intent(this@MainActivity, SignupActivity::class.java)
                startActivity(intent)
            }
    
            articleButton.setOnClickListener {
                val intent = Intent(this@MainActivity, ArticleActivity::class.java)
                startActivity(intent)
            }
        }
    }
