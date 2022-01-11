package com.example.fragmentexample

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    //ListFragment에 보내주기위해 !
    lateinit var listFragment: ListFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment()

        binding.btnSend.setOnClickListener {
            listFragment.setValue("acti->frag전달 값")

        }
    }

    //초기 fragment_list를 띄워주는 함수 _ OnCreate에서 실행됨
    fun setFragment() {
        //Fragment객체 생성!

        listFragment = ListFragment()
        //프래그먼트에 값 전달을 위한 bundle
        var bundle = Bundle()
        bundle.putString("key1", "양준식~")
        bundle.putInt("key2", 20220420)
        //생성해놓은 fragment객체에 bundle로 값 전달
        listFragment.arguments = bundle

        //fragment 생성을 위해 트랜잭션 생성
        val transaction = supportFragmentManager.beginTransaction()
        //Main layout의 frameLayout이라는 id를 가진 frameLayout 에 listFragment객체를 전달
        transaction.add(R.id.frameLayout, listFragment)
        //적용
        transaction.commit()
    }

    // Detail fragment로 바꿔서 띄움
    //결국 "판"은 activiti_main에 있는 것이므로, frameLayout에 새로운 fragment를 전달해주기만 하면 되는 것
    fun goDetail(){
        val detailFragment = DetailFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, detailFragment)
        //addToBackStack없이 back버튼을 누르면 앱 내려감
        transaction.addToBackStack("detail")
        transaction.commit()
    }

    // Detail에서 Back 버튼( 화면에 달아놓은 버튼 ) 을 누른 경우 다시 fragment_list를 띄움
    fun goBack(){
        onBackPressed()
    }
}