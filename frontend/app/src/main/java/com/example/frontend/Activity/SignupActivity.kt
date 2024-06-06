package com.example.frontend.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.frontend.App
import com.example.frontend.Data.LoginResponse
import com.example.frontend.Data.User
import com.example.frontend.R
import com.example.frontend.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        val IdText = findViewById<EditText>(R.id.user_id)
        val PasswordText = findViewById<EditText>(R.id.user_pw)
        val SignupButton = findViewById<Button>(R.id.signupbutton)
        val LoginButton = findViewById<Button>(R.id.loginbutton)
        val LogoutButton = findViewById<Button>(R.id.logoutbutton)

        LogoutButton.setOnClickListener {
            App.prefs.clearToken()
            Toast.makeText(this@SignupActivity, "로그아웃 성공", Toast.LENGTH_SHORT).show()
        }

        SignupButton.setOnClickListener {
            val id = IdText.text.toString()
            val password = PasswordText.text.toString()

            if (id.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "모든 필드를 채워주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val signup = User(id,password)
                Signup(signup)
            } catch (e : Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        LoginButton.setOnClickListener {
            val id = IdText.text.toString()
            val password = PasswordText.text.toString()

            if (id.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "모든 필드를 채워주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val login = User(id,password)
                Login(login)
            } catch (e : Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun Signup(signup: User) {

        val call = RetrofitBuilder.api.signup(signup)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@SignupActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@SignupActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@SignupActivity, "Error: " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun Login(login: User) {
        val call = RetrofitBuilder.api.login(login)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        App.prefs.token = loginResponse.accessToken
                        Toast.makeText(this@SignupActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@SignupActivity, "로그인 응답이 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@SignupActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@SignupActivity, "Error: " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}