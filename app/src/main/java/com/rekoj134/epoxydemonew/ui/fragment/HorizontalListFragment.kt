package com.rekoj134.epoxydemonew.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rekoj134.epoxydemonew.R
import com.rekoj134.epoxydemonew.databinding.FragmentHorizontalListBinding
import com.rekoj134.epoxydemonew.epoxy.controller.HorizontalListController
import com.rekoj134.epoxydemonew.util.DataUtil

class HorizontalListFragment : Fragment(R.layout.fragment_horizontal_list) {
    private lateinit var binding: FragmentHorizontalListBinding
    private lateinit var controller: HorizontalListController
    private lateinit var layoutManager: GridLayoutManager
    private var spanCount = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHorizontalListBinding.bind(view)
        setupRecyclerView()
        handleEvent()
    }

    private fun handleEvent() {
        binding.btnPlus.setOnClickListener {
            spanCount++
            layoutManager.spanCount = spanCount
            controller.spanCount = layoutManager.spanCount
            layoutManager.spanSizeLookup = controller.spanSizeLookup
        }

        binding.btnMinus.setOnClickListener {
            if (spanCount > 1) {
                spanCount--
                layoutManager.spanCount = spanCount
                controller.spanCount = layoutManager.spanCount
                layoutManager.spanSizeLookup = controller.spanSizeLookup
            }
        }
    }

    private fun setupRecyclerView() {
        controller = HorizontalListController()
        layoutManager = GridLayoutManager(requireContext(), spanCount, RecyclerView.HORIZONTAL, false)
        controller.spanCount = spanCount
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        binding.verticalRecyclerView.layoutManager = layoutManager

        binding.verticalRecyclerView.setControllerAndBuildModels(controller)

        Handler(Looper.getMainLooper()).postDelayed({
            controller.listImage = DataUtil.getVerticalListData1()
        }, 2000)
        Handler(Looper.getMainLooper()).postDelayed({
            controller.listImage.clear()
            controller.listImage = DataUtil.getVerticalListData2()
        }, 6000)
    }

    companion object {
        fun newInstance(): HorizontalListFragment {
            val args = Bundle()
            val fragment: HorizontalListFragment = HorizontalListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}