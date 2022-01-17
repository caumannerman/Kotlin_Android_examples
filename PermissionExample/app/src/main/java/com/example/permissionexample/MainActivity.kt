package com.example.permissionexample


import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permissionexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnCamera.setOnClickListener { checkPermission() }
    }


    //former "camera permission" check!! df
    fun checkPermission(){
        //former "camera permission" check!!
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        // now we check if former cameraPermission is true or false
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            // true -> start process
            startProcess()
        } else {
            // not true -> we have to request permission to User by requestPermission()
            requestPermission()
        }
    }

    fun startProcess(){
        Toast.makeText(this, "camera start", Toast.LENGTH_LONG).show()
    }

    fun requestPermission() {
        // result -> onRequestPermissionsResult !!
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 99)
    }

    // upon Users choice ( DENY , ALLOW Camera Access )
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when(requestCode) {
            99 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startProcess()
                } else {
                    finish()
                }
            }
        }
    }
}