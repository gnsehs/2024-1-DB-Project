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
    
            val call = RetrofitBuilder.api.getGames()
    
            call.enqueue(object : Callback<List<Games>> {
                override fun onResponse(call: Call<List<Games>>, response: Response<List<Games>>) {
                    if (response.isSuccessful) {
                        gamesList = response.body() ?: emptyList()
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "Get games successfully", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "Get games failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                    Log.d("MainActivity", "onResponse called")
                }
    
                override fun onFailure(call: Call<List<Games>>, t: Throwable) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Failed to retrieve games", Toast.LENGTH_SHORT).show()
                    }
                    Log.d("MainActivity", "onFailure called", t)
                }
            })
    
            val loginButton: Button = findViewById(R.id.loginbutton)
            val signupButton: Button = findViewById(R.id.signupbutton)
            val articleButton: Button = findViewById(R.id.articlebutton)
            val dbButton: Button = findViewById(R.id.dbbutton)
    
            loginButton.setOnClickListener {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }

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
//                intent.putExtra("gameList", ArrayList(gamesList))
                startActivity(intent)
            }
        }
    }
    
    fun <T> Call<T>.enqueue(callback: Callback<List<Games>>) {
    
    }
