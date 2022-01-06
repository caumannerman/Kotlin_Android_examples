package com.example.spinnerexample

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.spinnerexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val data = listOf(" - select - ", "1월", "2월", "3월", "4월", "5월", "6월")
        val adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1,data)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("mymy", "${p0} ,  view: ${view},  position: ${position},  id: ${id}")
                binding.result.text = data[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}