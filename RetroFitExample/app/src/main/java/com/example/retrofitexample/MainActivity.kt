package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitexample.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root )

        val adapter = CustomAdapter()
        binding.rvProfile.adapter = adapter
        binding.rvProfile.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com").addConverterFactory(
            GsonConverterFactory.create()).build()

        //요청버튼 클릭 시, retrofit이용해 데이터를 불러와 adapter에 세팅
        binding.btnRequest.setOnClickListener {
            val githubService = retrofit.create(GithubService::class.java)
            githubService.users().enqueue(object: Callback<Repository> {
                override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                    adapter.userList = response.body() as Repository
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Repository>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}

//Retrofit 인터페이스
interface GithubService {
    @GET("users/Kotlin/repos")
    fun users(): Call<Repository>
}