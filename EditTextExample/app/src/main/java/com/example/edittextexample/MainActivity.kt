package com.example.edittextexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.example.edittextexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.etRealtime.addTextChangedListener{
            Log.d("EditTetx", "현재 입력된 값 = ${it.toString()}")
            binding.tvShow.text = it.toString()
        }

    }
}