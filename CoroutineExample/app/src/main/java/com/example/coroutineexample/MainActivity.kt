package com.example.coroutineexample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.coroutineexample.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


suspend fun loadImage(imageUrl: String): Bitmap {
    val url = URL(imageUrl)
    val stream = url.openStream()
    return BitmapFactory.decodeStream(stream)
}
class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run{
            btnDownload.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    binding.progress.visibility = View.VISIBLE
                    //사용자가 edit text에 입력한 url 가져옴
                    val url = binding.etUrl.text.toString()

                    val bitmap = withContext(Dispatchers.IO) {
                        loadImage(url)
                    }
                    ivPreview.setImageBitmap(bitmap)
                    progress.visibility = View.GONE
                }
            }
        }
    }
}