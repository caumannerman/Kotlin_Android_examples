package com.example.checkbuttonexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import com.example.checkbuttonexample.databinding.ActivityMainBinding

private var checkCount: Int = 0
private var checkSet = mutableSetOf<String>()

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // 체크버튼 여러개에 대하여 한 번에 작성하도록 직접 리스너 작성
    val listener by lazy { CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        //누르는 경우
        if(isChecked) {
            when (buttonView.id) {
                R.id.cb_apple -> {
                    checkSet.add("apple")
                }
                R.id.cb_banana -> {
                    checkSet.add("banana")
                }
                R.id.cb_orange -> {
                    checkSet.add("orange")
                }
            }
            checkCount += 1
            when(checkCount){
                in 0..1 -> binding.tvShow.text = "${checkCount}두 개 선택하세요\n" +
                        " ${checkSet}"
                2 -> binding.tvShow.text = "${checkCount}잘 하셨습니다.\n" +
                        " ${checkSet}"
                else -> binding.tvShow.text = "${checkCount}너무 많이 선택하셨습니다.\n" +
                        " ${checkSet}"
            }
        }
        //체크 해제하는 경우
        else{
            when (buttonView.id) {
                R.id.cb_apple -> {
                    checkSet.remove("apple")
                }
                R.id.cb_banana -> {
                    checkSet.remove("banana")
                }
                R.id.cb_orange -> {
                    checkSet.remove("orange")
                }
            }
            checkCount -= 1
            when(checkCount){
                in 0..1 -> binding.tvShow.text = "${checkCount}두 개 선택하세요 \n ${checkSet}"
                2 -> binding.tvShow.text = "${checkCount}잘 하셨습니다.\n" +
                        " ${checkSet}"
                else -> binding.tvShow.text = "${checkCount}너무 많이 선택하셨습니다. \n" +
                        " ${checkSet}"
            }
        }
    }}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.cbApple.setOnCheckedChangeListener (listener)
        binding.cbBanana.setOnCheckedChangeListener(listener)
        binding.cbOrange.setOnCheckedChangeListener(listener)

    }

}
