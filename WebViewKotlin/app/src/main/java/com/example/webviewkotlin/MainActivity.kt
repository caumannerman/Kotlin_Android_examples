package com.example.webviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.example.webviewkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initWebView()
    }

    override fun onBackPressed() {
        if ( binding.wvWebview.canGoBack()) {
            binding.wvWebview.goBack()
        }
        else {
            finish()
        }
    }

    fun initWebView() {
        binding.wvWebview.apply{
            //js enable
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            /* webveiw no new window */
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }
        //load URL
        binding.wvWebview.loadUrl("https://www.naver.com")
    }

}


