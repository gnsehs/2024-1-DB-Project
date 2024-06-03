package com.example.frontend.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.frontend.Data.Article
import com.example.frontend.R
import com.example.frontend.RetrofitBuilder_post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article)

        val titleEditText = findViewById<EditText>(R.id.title)
        val contentEditText = findViewById<EditText>(R.id.content)
        val gameIdEditText = findViewById<EditText>(R.id.game_id)
        val postButton = findViewById<Button>(R.id.post)

        postButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()
            val gameIdString = gameIdEditText.text.toString()

            if (title.isEmpty() || content.isEmpty() || gameIdString.isEmpty()) {
                Toast.makeText(this, "모든 필드를 채워주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val gameId = gameIdString.toInt()
                val article = Article(title, content, gameId)
                sendPostRequest(article)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "유효한 Game ID를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendPostRequest(article: Article) {
        val call = RetrofitBuilder_post.api.postArticle(article)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ArticleActivity, "Article posted successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ArticleActivity, "Failed to post article", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ArticleActivity, "Error: " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
