package com.example.handlerlooperexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.handlerlooperexample.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    var total = 0
    //이 started라는 변수를 기준으로 각 thread가 간접적으로 소통하게 된다고 볼 수 있다.
    var started = false

    //핸들러 생성
    val handler = object: Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            val minute = String.format("%02d", total/60)
            val second = String.format("%02d", total%60)
            binding.tvTimer.text = "$minute:$second"
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            started = true

            //thread를 실행하는 여러 방법 중 하나
            thread(start=true){
                while (started) {
                    Thread.sleep(1000)
                    if(started) {
                        total = total + 1
                        handler?.sendEmptyMessage(0)
                    }
                }
            }
        }


        //종료 버튼
        binding.btnFinish.setOnClickListener{
            if(started) {
                started = false
                //total 시간 초기화
                total = 0
                //시간 초기화
                binding.tvTimer.text = "00:00"
            }
        }

    }
}