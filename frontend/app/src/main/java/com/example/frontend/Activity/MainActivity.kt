    package com.example.frontend.Activity
    
    import android.os.Bundle
    import android.content.Intent
    import android.widget.Button
    import androidx.appcompat.app.AppCompatActivity
    import com.example.frontend.Activity.Recycler.ArticleActivity
    import com.example.frontend.Activity.Recycler.CompanyActivity
    import com.example.frontend.Activity.Recycler.DeviceActivity
    import com.example.frontend.Activity.Recycler.GameActivity
    import com.example.frontend.Data.Devices
    import com.example.frontend.Data.Gamecompanys
    import com.example.frontend.Data.Games
    import com.example.frontend.R

    class MainActivity : AppCompatActivity() {
    
        private lateinit var gamesList: List<Games>
        private lateinit var companyList: List<Gamecompanys>
        private lateinit var deviceList: List<Devices>
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val signupButton: Button = findViewById(R.id.signupbutton)
            val writeButton: Button = findViewById(R.id.writebutton)
            val gameButton: Button = findViewById(R.id.gamebutton)
            val deviceButton: Button = findViewById(R.id.devicebutton)
            val companyButton: Button = findViewById(R.id.companybutton)
            val articleButton: Button = findViewById(R.id.articlebutton)

            gameButton.setOnClickListener {
                val intent = Intent(this@MainActivity, GameActivity::class.java)
                startActivity(intent)
            }

            companyButton.setOnClickListener {
                val intent = Intent(this@MainActivity, CompanyActivity::class.java)
                startActivity(intent)
            }

            deviceButton.setOnClickListener {
                val intent = Intent(this@MainActivity, DeviceActivity::class.java)
                startActivity(intent)
            }
    
            signupButton.setOnClickListener {
                val intent = Intent(this@MainActivity, SignupActivity::class.java)
                startActivity(intent)
            }
    
            writeButton.setOnClickListener {
                val intent = Intent(this@MainActivity, WriteActivity::class.java)
                startActivity(intent)
            }

            articleButton.setOnClickListener {
                val intent = Intent(this@MainActivity, ArticleActivity::class.java)
                startActivity(intent)
            }
        }
    }
