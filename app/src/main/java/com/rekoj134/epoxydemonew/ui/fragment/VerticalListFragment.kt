package com.rekoj134.epoxydemonew.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyTouchHelper
import com.rekoj134.epoxydemonew.R
import com.rekoj134.epoxydemonew.databinding.FragmentVerticalListBinding
import com.rekoj134.epoxydemonew.epoxy.controller.VerticalListController
import com.rekoj134.epoxydemonew.itemImage
import com.rekoj134.epoxydemonew.itemLoadingItem
import com.rekoj134.epoxydemonew.util.DataUtil

class VerticalListFragment : Fragment(R.layout.fragment_vertical_list) {
    private lateinit var binding: FragmentVerticalListBinding
    private lateinit var controller: VerticalListController
    private lateinit var layoutManager: GridLayoutManager
    private var spanCount = 2

    private val listData = DataUtil.getVerticalListData1()
    private val favListData = ArrayList<com.rekoj134.epoxydemonew.model.Image>()
    private val favListData2 = ArrayList<com.rekoj134.epoxydemonew.model.Image>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentVerticalListBinding.bind(view)
        setupRecyclerView()
        handleEvent()

//        binding.verticalRecyclerView.requestModelBuild()
        binding.verticalRecyclerView.withModels {

            if (listData.isEmpty()){
                itemLoadingItem {  }
            return@withModels
            }

            listData.take(5).forEach { image ->
                itemImage {
                    id(image.id)
                    url(image.src)

                    isFavorite(image.isSelected)
                    onClick { v ->
                        image.copy(id, )
                        favListData.add(image)
                        binding.verticalRecyclerView.requestModelBuild()
                    }
                }
            }

            favListData2.take(5).forEach { image ->
                itemImage {
                    id(image.id)
                    url(image.src)

                    isFavorite(image.isSelected)
                    onClick { v ->
                        image.copy(id, )
                        favListData.add(image)
                        binding.verticalRecyclerView.requestModelBuild()
                    }
                }
            }

            itemLoadingItem {  }
        }
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
        controller = VerticalListController()

        EpoxyTouchHelper.initDragging(controller)
            .withRecyclerView(binding.verticalRecyclerView)
            .forGrid()
            .forAllModels()
            .andCallbacks(object : EpoxyTouchHelper.DragCallbacks<EpoxyModel<*>>() {
                override fun onModelMoved(
                    fromPosition: Int,
                    toPosition: Int,
                    modelBeingMoved: EpoxyModel<*>?,
                    itemView: View?
                ) {
                    Log.d("ANCUTKO", "$fromPosition - $toPosition")
                    val removed = controller.listImage.removeAt(fromPosition)
                    controller.listImage.add(toPosition, removed)
                    controller.requestModelBuild()
                }
            })

        layoutManager = GridLayoutManager(requireContext(), spanCount, RecyclerView.VERTICAL, false)
        controller.spanCount = spanCount
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        binding.verticalRecyclerView.layoutManager = layoutManager

        binding.verticalRecyclerView.setControllerAndBuildModels(controller)

        Handler(Looper.getMainLooper()).postDelayed({
            controller.listImage = DataUtil.getVerticalListData1()
        }, 1000)
        Handler(Looper.getMainLooper()).postDelayed({
            controller.listImage.clear()
            controller.listImage = DataUtil.getVerticalListData2()
        }, 3000)
    }

    companion object {
        fun newInstance(): VerticalListFragment {
            val args = Bundle()
            val fragment: VerticalListFragment = VerticalListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}