package com.rekoj134.epoxydemonew.epoxy.controller

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.rekoj134.epoxydemonew.ItemImageBindingModel_
import com.rekoj134.epoxydemonew.ItemLoadingItemBindingModel_
import com.rekoj134.epoxydemonew.epoxy.extension.carousel
import com.rekoj134.epoxydemonew.epoxy.extension.withModelsFrom
import com.rekoj134.epoxydemonew.itemLoadingItem
import com.rekoj134.epoxydemonew.itemTitleSelectAll
import com.rekoj134.epoxydemonew.model.Image

class TwoTypeList1Controller : AsyncEpoxyController() {
    var listImage1: ArrayList<Image> = ArrayList()
        set(value) {
            field = value
            requestModelBuild()
        }

    var listImage2: ArrayList<Image> = ArrayList()
        set(value) {
            field = value
            requestModelBuild()
        }

    var listImage3: ArrayList<Image> = ArrayList()
        set(value) {
            field = value
            requestModelBuild()
        }

    var listImage4: ArrayList<Image> = ArrayList()
        set(value) {
            field = value
            requestModelBuild()
        }


    override fun buildModels() {
        Carousel.setDefaultGlobalSnapHelperFactory(object : Carousel.SnapHelperFactory() {
            override fun buildSnapHelper(context: Context?): SnapHelper {
                return GravitySnapHelper(Gravity.CENTER)
            }
        })

        itemTitleSelectAll {
            id("title_select_all_1")
            title("List 1")
            onClick { _ ->
                this@TwoTypeList1Controller.onSelectAll(1)
            }
        }

        if (listImage1.isEmpty()) {
            itemLoadingItem {
                id("loading_list_1")
            }
        } else {
            val listModel_ = ArrayList<DataBindingEpoxyModel>()
            carousel {
                id("list_1_container")
                numViewsToShowOnScreen(2.5f)
                this@TwoTypeList1Controller.listImage1.forEach {
                    listModel_.add(ItemImageBindingModel_().id("list_1_" + it.id).imageItem(it).onClick { _ ->
                        this@TwoTypeList1Controller.onItemSelected(it, 1)
                    })
                }
                if (listModel_.size < 6) listModel_.add(ItemLoadingItemBindingModel_().id("loading_item_in_list_1"))
                models(listModel_)
            }
        }

        itemTitleSelectAll {
            id("title_select_all_2")
            title("List 2")
            onClick { _ ->
                this@TwoTypeList1Controller.onSelectAll(2)
            }
        }

        if (listImage2.isEmpty()) {
            itemLoadingItem {
                id("loading_list_2")
            }
        } else {
            carousel {
                id("list_2_container")
                numViewsToShowOnScreen(3f)

                withModelsFrom(this@TwoTypeList1Controller.listImage2) {
                    ItemImageBindingModel_().id("list_2_" + it.id).imageItem(it).onClick { _ ->
                        this@TwoTypeList1Controller.onItemSelected(it, 2)
                    }
                }
            }
        }

        itemTitleSelectAll {
            id("title_select_all_3")
            title("List 3")
            onClick { _ ->
                this@TwoTypeList1Controller.onSelectAll(3)
            }
        }

        if (listImage3.isEmpty()) {
            itemLoadingItem {
                id("loading_list_3")
            }
        } else {
            carousel {
                id("list_3_container")
                numViewsToShowOnScreen(3.5f)

                withModelsFrom(this@TwoTypeList1Controller.listImage3) {
                    ItemImageBindingModel_().id("list_3_" + it.id).imageItem(it).onClick { _ ->
                        this@TwoTypeList1Controller.onItemSelected(it, 3)
                    }
                }
            }
        }

        itemTitleSelectAll {
            id("title_select_all_4")
            title("List 4")
            onClick { _ ->
                this@TwoTypeList1Controller.onSelectAll(4)
            }
        }

        if (listImage4.isEmpty()) {
            itemLoadingItem {
                id("loading_list_4")
            }
        } else {
            carousel {
                id("list_4_container")
                numViewsToShowOnScreen(4f)

                withModelsFrom(this@TwoTypeList1Controller.listImage4) {
                    ItemImageBindingModel_().id("list_4_" + it.id).imageItem(it).onClick { _ ->
                        this@TwoTypeList1Controller.onItemSelected(it, 4)
                    }
                }
            }
        }
    }

    private fun onSelectAll(listPosition: Int) {
        val tempList = ArrayList<Image>()
        val listForSearch = when (listPosition) {
            1 -> listImage1
            2 -> listImage2
            3 -> listImage3
            4 -> listImage4
            else -> listImage1
        }
        listForSearch.forEach {
            val tempItem = it.copy()
            tempItem.isSelected = true
            tempList.add(tempItem)
        }
        Log.e("ANCUTKO", "temp " + tempList.toString())
        when (listPosition) {
            1 -> listImage1 = tempList
            2 -> listImage2 = tempList
            3 -> listImage3 = tempList
            4 -> listImage4 = tempList
            else -> listImage1 = tempList
        }
    }

    private fun onItemSelected(imageSelected: Image, listPosition: Int) {
        val tempList = ArrayList<Image>()
        val listForSearch = when (listPosition) {
            1 -> listImage1
            2 -> listImage2
            3 -> listImage3
            4 -> listImage4
            else -> listImage1
        }
        listForSearch.forEach {
            if (it.id == imageSelected.id) {
                val tempItem = it.copy()
                tempItem.isSelected = !tempItem.isSelected
                tempList.add(tempItem)
            } else {
                tempList.add(it)
            }
        }
        Log.e("ANCUTKO", "temp " + tempList.toString())
        when (listPosition) {
            1 -> listImage1 = tempList
            2 -> listImage2 = tempList
            3 -> listImage3 = tempList
            4 -> listImage4 = tempList
            else -> listImage1 = tempList
        }
    }
}