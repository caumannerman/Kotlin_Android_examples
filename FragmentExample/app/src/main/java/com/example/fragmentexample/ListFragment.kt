package com.example.fragmentexample

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.fragment.app.FragmentManager
import com.example.fragmentexample.databinding.FragmentListBinding

//
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListFragment : Fragment() {
//
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    //mainActivity의 goDetail(프래그먼트 전환) 함수를 사용해야하므로, mainActivity객체를 담아둘 변수 만듦
    var mainActivity: MainActivity? = null


    lateinit var binding:FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.btnNext.setOnClickListener {mainActivity?.goDetail()}
        //bundle로 전달받은 argument 꺼내쓰는 코드
        binding.tvArg1.text = arguments?.getString("key1")
        binding.tvArg2.text = "${arguments?.getInt("key2")}"

        return binding.root
    }

    //프래그먼트 생명주기 메서드... -> 보통 이를 통해 Activity코드를 전달받는다.
    //context에는 부모 Activity전체가 담겨있다
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) mainActivity = context
    }

    //Activity에서 이미 생성된 fragment내의 값을 변경하기 위한 함수 구현
    fun setValue(value: String) {
        // activity에서 이 함수를 call하여, value로 전달한 값을 가져옴
        binding.tvSend.text = value

    }


//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment ListFragment.
//         */
//
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ListFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}