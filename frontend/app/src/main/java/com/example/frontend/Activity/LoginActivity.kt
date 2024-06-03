package com.example.frontend.Activity

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.frontend.R

class LoginActivity : AppCompatActivity() {
    private lateinit var mWebView: WebView
    private lateinit var mWebSettings: WebSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // WebView 객체를 초기화합니다.
        mWebView = findViewById(R.id.webView) // login.xml에서 webView의 id가 "webView"라고 가정합니다.
        mWebSettings = mWebView.settings

        // WebView 설정을 구성합니다.
        mWebSettings.javaScriptEnabled = true // JavaScript를 사용 가능하게 합니다.
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true

        mWebView.webViewClient = WebViewClient()
        mWebView.webChromeClient = WebChromeClient()

        // 올바른 URL을 로드합니다.
        mWebView.loadUrl("http://10.0.2.2:8080/login")
    }
}
