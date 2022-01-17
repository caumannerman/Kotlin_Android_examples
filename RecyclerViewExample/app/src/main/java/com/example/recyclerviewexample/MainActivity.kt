package com.example.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.databinding.ActivityMainBinding
import com.example.recyclerviewexample.databinding.ItemRecyclerBinding


class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}


    fun loadData(): MutableList<Memo> {
        val data: MutableList<Memo> = mutableListOf()

        for (i in 1..100){
            val title = "데이터클래스에 들어갈 제목${i}"
            val date = System.currentTimeMillis()
            var memo = Memo(i, title, date)
            data.add(memo)
            

        }
        return data
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}

