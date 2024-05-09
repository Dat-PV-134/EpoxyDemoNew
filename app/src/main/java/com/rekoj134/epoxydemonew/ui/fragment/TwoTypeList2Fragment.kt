package com.rekoj134.epoxydemonew.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rekoj134.epoxydemonew.R
import com.rekoj134.epoxydemonew.databinding.FragmentTwoTypeList2Binding
import com.rekoj134.epoxydemonew.epoxy.controller.TwoTypeList1Controller
import com.rekoj134.epoxydemonew.epoxy.controller.TwoTypeList2Controller
import com.rekoj134.epoxydemonew.epoxy.controller.VerticalListController
import com.rekoj134.epoxydemonew.util.DataUtil

class TwoTypeList2Fragment: Fragment(R.layout.fragment_two_type_list_2) {
    private lateinit var binding: FragmentTwoTypeList2Binding
    private lateinit var controller: TwoTypeList2Controller
    private lateinit var layoutManager: GridLayoutManager
    private var spanCount = 3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTwoTypeList2Binding.bind(view)
        setupRecyclerView()
        handleEvent()
    }

    private fun handleEvent() {

    }

    private fun setupRecyclerView() {
        controller = TwoTypeList2Controller()
        layoutManager = GridLayoutManager(requireContext(), spanCount, RecyclerView.VERTICAL, false)
        controller.spanCount = spanCount
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        binding.twoType2RecyclerView.layoutManager = layoutManager

        binding.twoType2RecyclerView.setControllerAndBuildModels(controller)
        controller = TwoTypeList2Controller()
        controller.listImage1 = DataUtil.getListTwoType2()

        binding.twoType2RecyclerView.setControllerAndBuildModels(controller)
        Handler(Looper.getMainLooper()).postDelayed({
            controller.listImage2 = DataUtil.getListTwoType2Different1()
            controller.listImage3 = DataUtil.getListTwoType2Different2()
            controller.listImage4 = DataUtil.getListTwoType2Different3()
        }, 3000)
    }

    companion object {
        fun newInstance(): TwoTypeList2Fragment {
            val args = Bundle()
            val fragment: TwoTypeList2Fragment = TwoTypeList2Fragment()
            fragment.arguments = args
            return fragment
        }
    }
}
