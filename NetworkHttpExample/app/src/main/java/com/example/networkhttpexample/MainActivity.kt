package com.example.networkhttpexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.networkhttpexample.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRequest.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    var urlText = binding.etUrl.text.toString()
                    //앞에 https://없으면 붙여줌
                    if (!urlText.startsWith("https")) {
                        urlText = "https://${urlText}"
                    }

                    // URL객체로 생성
                    val url = URL(urlText)
                    //URLConnection이 반환(추상 클래스)  이를 구현클래스인 HttpURL..로 변환
                    val urlConnection = url.openConnection() as HttpURLConnection
                    // 연결된 커넥션에 요청방식 설정
                    urlConnection.requestMethod = "GET"
                    //응답이 정상이면 응답 데이터 처리
                    if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                        // 입력 스트림을 연결, 버퍼에 담아 데이터 읽을 준비 함
                        val streamReader = InputStreamReader(urlConnection.inputStream)
                        val buffered = BufferedReader(streamReader)
                        //데이터를 읽어 content에 저장
                        val content = StringBuilder()
                        while (true) {
                            //null이면 나옴
                            val line = buffered.readLine() ?: break
                            content.append(line)
                        }
                        buffered.close()
                        urlConnection.disconnect()

                        //textView에 입력
                        launch(Dispatchers.Main) {
                            binding.tvResult.text = content.toString()
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}