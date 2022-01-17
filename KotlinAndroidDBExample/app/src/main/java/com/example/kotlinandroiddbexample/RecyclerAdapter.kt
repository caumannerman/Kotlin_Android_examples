package com.example.kotlinandroiddbexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinandroiddbexample.databinding.ItemRecyclerBinding

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var helper: SqliteHelper? = null
    var listData = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

        init{
            binding.btnDelete.setOnClickListener {
                helper?.deleteMemo(mMemo!!)
                listData.remove(mMemo)
                notifyDataSetChanged()
            }
        }
        var mMemo: Memo? = null

        //매개변수 memo에는 adapter에서 넘겨준 data가 들어감.
        // 이 것을 holder내의 변수 mMemo에 저장해두는 것 --> why?
        fun setMemo(memo: Memo) {
            binding.tvNo.text = "${memo.no}"
            binding.tvContent.text = memo.content
            binding.tvDatetime.text = "${memo.datetime}"
            this.mMemo = memo

        }
    }


}


