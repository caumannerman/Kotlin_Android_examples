package com.example.kotlinandroiddbexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinandroiddbexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    //db에서 데이터를 갖고오기 위해 SqliteHelper객체 생성
    val helper = SqliteHelper(this, "memo", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = RecyclerAdapter()
        adapter.helper = helper

        //db에서 가져온 data모두 넣어줌
        adapter.listData.addAll(helper.selectMemo())

        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        //저장 버튼에 리스터
        binding.btnSave.setOnClickListener {
            if(binding.editMemo.text.toString().isNotEmpty()) {
                val memo = Memo(null, binding.editMemo.text.toString(), System.currentTimeMillis())
                helper.insertMemo(memo)
                //데이터 삭제
                adapter.listData.clear()
                //추가된 데이터까지 함께 읽어옴
                adapter.listData.addAll(helper.selectMemo())
                //갱신
                adapter.notifyDataSetChanged()
                binding.editMemo.setText("")
            }
        }
    }

}