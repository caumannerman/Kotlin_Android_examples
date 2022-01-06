package com.example.kotlinandroid_emailvalidation

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.kotlinandroid_emailvalidation.databinding.ActivityMainBinding

import java.util.regex.Pattern


//val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
const val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"


class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("mymy", "1: ${p0},  2: ${p1},  3: ${p2},  4: ${p3},")
                checkEmail()
                when (p1 + p3) {
                    in 0..20 -> { binding.textnum.setText("${p1+p3}/ 20")}
                    else -> { binding.textnum.setText("최대 20자 가능!") }
                }
            }
        })
    }
    fun checkEmail(){
        val email = binding.email.text.toString().trim() //공백제거
        val p = Pattern.matches(emailValidation, email) // 서로 패턴이 맞닝?
        if (p) {
            //이메일 형태가 정상일 경우
//            binding.email.setTextColor(R.color.black.toInt())
            binding.email.setTextColor(Color.BLACK)
            binding.result.setText("Email  Valid!")
            binding.result.setTextColor(R.color.black.toInt())
        } else {
//            binding.email.setTextColor(-65536)
            binding.email.setTextColor(Color.parseColor("#ff0000"))
            binding.result.setText("Email not Valid!")
            binding.result.setTextColor(-65536)
        }
    }


}