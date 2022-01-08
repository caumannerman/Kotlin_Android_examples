package com.example.progressbarexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.progressbarexample.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //start=true로 하면 백그라운드(서브 스레드)에서 동작
        thread(start=true) {
            Thread.sleep(5000)
            //UI를 그리는 메인쓰레드에서 실행시켜야(그려야) 앱이 죽지 않음 - 백그라운드가 UI를 그리려고 하면 죽음
            runOnUiThread{
                showProgress(false)
            }

        }
    }


    fun showProgress(show: Boolean){
        //View.INVISIBLE -> 자리는 차지, 안보임 /   View.GONE -> 자리도 차지X, 안보임  / VISIBLE -> 보임
        binding.progress.visibility = if(show) View.VISIBLE else View.GONE
    }
}
