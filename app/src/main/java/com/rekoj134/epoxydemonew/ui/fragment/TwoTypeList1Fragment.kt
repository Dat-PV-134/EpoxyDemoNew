package com.rekoj134.epoxydemonew.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.rekoj134.epoxydemonew.R
import com.rekoj134.epoxydemonew.databinding.FragmentTwoTypeList1Binding
import com.rekoj134.epoxydemonew.epoxy.controller.TwoTypeList1Controller
import com.rekoj134.epoxydemonew.util.DataUtil

class TwoTypeList1Fragment : Fragment(R.layout.fragment_two_type_list_1) {
    private lateinit var binding: FragmentTwoTypeList1Binding
    private lateinit var controller: TwoTypeList1Controller

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTwoTypeList1Binding.bind(view)
        setupRecyclerView()
        handleEvent()
    }

    private fun handleEvent() {

    }

    private fun setupRecyclerView() {
        controller = TwoTypeList1Controller()

        controller.listImage1 = DataUtil.getVerticalListData1()

        binding.twoType1RecyclerView.setControllerAndBuildModels(controller)
        Handler(Looper.getMainLooper()).postDelayed({
            controller.listImage1 = DataUtil.getVerticalListData2()
            controller.listImage2 = DataUtil.getVerticalListData2()
            controller.listImage3 = DataUtil.getVerticalListData2()
            controller.listImage4 = DataUtil.getVerticalListData2()
        }, 3000)
    }

    companion object {
        fun newInstance(): TwoTypeList1Fragment {
            val args = Bundle()
            val fragment: TwoTypeList1Fragment = TwoTypeList1Fragment()
            fragment.arguments = args
            return fragment
        }
    }
}