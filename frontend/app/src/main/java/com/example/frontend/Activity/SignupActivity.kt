package com.example.frontend.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.frontend.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SignupActivity : AppCompatActivity() {

    private lateinit var userIdEditText: EditText
    private lateinit var userPwEditText: EditText
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        userIdEditText = findViewById(R.id.user_id)
        userPwEditText = findViewById(R.id.user_pw)
        signupButton = findViewById(R.id.signupbutton)

        signupButton.setOnClickListener{
            val input = HashMap<Any?, Any?>()
            input["username"] = userIdEditText.text.toString()
            input["password"] = userPwEditText.text.toString()

            val jsonObject = JSONObject(input)

            Toast.makeText(applicationContext, jsonObject.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendDataToServer(jsonData: JSONObject) {
        val url = "http://10.0.2.2:8080/"

        val client = OkHttpClient()
        val body = RequestBody.create(MediaType.parse("application/json"), jsonData.toString())
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(applicationContext, "서버에 연결할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val responseData = response.body()?.string()
                    runOnUiThread {
                        Toast.makeText(applicationContext, "서버 응답: $responseData", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
