package com.rekoj134.epoxydemonew.epoxy.controller

import android.content.Context
import android.util.Log
import android.view.Gravity
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyController
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.rekoj134.epoxydemonew.ItemImageBindingModel_
import com.rekoj134.epoxydemonew.epoxy.extension.carousel
import com.rekoj134.epoxydemonew.epoxy.extension.withModelsFrom
import com.rekoj134.epoxydemonew.itemImage
import com.rekoj134.epoxydemonew.itemLoadingItem
import com.rekoj134.epoxydemonew.itemTitleSelectAll
import com.rekoj134.epoxydemonew.model.Image

class TwoTypeList2Controller : EpoxyController() {
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
                this@TwoTypeList2Controller.onSelectAll(1)
            }
            spanSizeOverride { totalSpanCount, position, itemCount ->
                totalSpanCount
            }
        }

        if (listImage1.isEmpty()) {
            itemLoadingItem {
                id("loading_list_1")
                spanSizeOverride { totalSpanCount, position, itemCount ->
                    totalSpanCount
                }
            }
        } else {
            listImage1.forEach { item ->
                itemImage {
                    id("list_1_" + item.id)
                    imageItem(item)
                    onClick { _ ->
                        this@TwoTypeList2Controller.onItemSelected(item, 1)
                    }
                    spanSizeOverride { totalSpanCount, position, itemCount ->
                        1
                    }
                }
            }
        }

        itemTitleSelectAll {
            id("title_select_all_2")
            title("List 2")
            onClick { _ ->
                this@TwoTypeList2Controller.onSelectAll(2)
            }
            spanSizeOverride { totalSpanCount, position, itemCount ->
                totalSpanCount
            }
        }

        if (listImage2.isEmpty()) {
            itemLoadingItem {
                id("loading_list_2")
                spanSizeOverride { totalSpanCount, position, itemCount ->
                    totalSpanCount
                }
            }
        } else {
            listImage2.forEach { item ->
                itemImage {
                    id("list_2_" + item.id)
                    imageItem(item)
                    onClick { _ ->
                        this@TwoTypeList2Controller.onItemSelected(item, 2)
                    }
                    spanSizeOverride { totalSpanCount, position, itemCount ->
                        1
                    }
                }
            }
        }

        itemTitleSelectAll {
            id("title_select_all_3")
            title("List 3")
            onClick { _ ->
                this@TwoTypeList2Controller.onSelectAll(3)
            }
            spanSizeOverride { totalSpanCount, position, itemCount ->
                totalSpanCount
            }
        }

        if (listImage3.isEmpty()) {
            itemLoadingItem {
                id("loading_list_3")
                spanSizeOverride { totalSpanCount, position, itemCount ->
                    totalSpanCount
                }
            }
        } else {
            listImage3.forEach { item ->
                itemImage {
                    id("list_3_" + item.id)
                    imageItem(item)
                    onClick { _ ->
                        this@TwoTypeList2Controller.onItemSelected(item, 3)
                    }
                    spanSizeOverride { totalSpanCount, position, itemCount ->
                        1
                    }
                }
            }
        }

        itemTitleSelectAll {
            id("title_select_all_4")
            title("List 4")
            onClick { _ ->
                this@TwoTypeList2Controller.onSelectAll(4)
            }
            spanSizeOverride { totalSpanCount, position, itemCount ->
                totalSpanCount
            }
        }

        if (listImage4.isEmpty()) {
            itemLoadingItem {
                id("loading_list_1")
                spanSizeOverride { totalSpanCount, position, itemCount ->
                    totalSpanCount
                }
            }
        } else {
            listImage4.forEach { item ->
                itemImage {
                    id("list_4_" + item.id)
                    imageItem(item)
                    onClick { _ ->
                        this@TwoTypeList2Controller.onItemSelected(item, 4)
                    }
                    spanSizeOverride { totalSpanCount, position, itemCount ->
                        1
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