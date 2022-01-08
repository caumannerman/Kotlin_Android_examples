package com.example.flowtextexamplemarquee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // isSelected를 하지 않으면 글이 흐르지 않는다
        findViewById<TextView>(R.id.tex).isSelected = true
    }
}