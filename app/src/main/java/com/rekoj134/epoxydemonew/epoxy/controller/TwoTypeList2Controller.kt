package com.rekoj134.epoxydemonew.epoxy.controller

import android.content.Context
import android.view.Gravity
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyController
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
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

    private var setImageSelected = HashSet<Int>()

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
                    url(item.src)
                    isSelected(this@TwoTypeList2Controller.setImageSelected.contains(item.id))
                    onClick { _ ->
                        if (this@TwoTypeList2Controller.setImageSelected.contains(item.id)) this@TwoTypeList2Controller.setImageSelected.remove(
                            item.id
                        )
                        else this@TwoTypeList2Controller.setImageSelected.add(item.id)
                        this@TwoTypeList2Controller.requestModelBuild()
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
                    url(item.src)
                    isSelected(this@TwoTypeList2Controller.setImageSelected.contains(item.id))
                    onClick { _ ->
                        if (this@TwoTypeList2Controller.setImageSelected.contains(item.id)) this@TwoTypeList2Controller.setImageSelected.remove(
                            item.id
                        )
                        else this@TwoTypeList2Controller.setImageSelected.add(item.id)
                        this@TwoTypeList2Controller.requestModelBuild()
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
                    url(item.src)
                    isSelected(this@TwoTypeList2Controller.setImageSelected.contains(item.id))
                    onClick { _ ->
                        if (this@TwoTypeList2Controller.setImageSelected.contains(item.id)) this@TwoTypeList2Controller.setImageSelected.remove(
                            item.id
                        )
                        else this@TwoTypeList2Controller.setImageSelected.add(item.id)
                        this@TwoTypeList2Controller.requestModelBuild()
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
                    url(item.src)
                    isSelected(this@TwoTypeList2Controller.setImageSelected.contains(item.id))
                    onClick { _ ->
                        if (this@TwoTypeList2Controller.setImageSelected.contains(item.id)) this@TwoTypeList2Controller.setImageSelected.remove(
                            item.id
                        )
                        else this@TwoTypeList2Controller.setImageSelected.add(item.id)
                        this@TwoTypeList2Controller.requestModelBuild()
                    }
                    spanSizeOverride { totalSpanCount, position, itemCount ->
                        1
                    }
                }
            }
        }
    }

    private fun onSelectAll(listPosition: Int) {
        when (listPosition) {
            1 -> {
                listImage1.forEach {
                    setImageSelected.add(it.id)
                }
            }
            2 -> {
                listImage2.forEach {
                    setImageSelected.add(it.id)
                }
            }
            3 -> {
                listImage3.forEach {
                    setImageSelected.add(it.id)
                }
            }
            4 -> {
                listImage4.forEach {
                    setImageSelected.add(it.id)
                }
            }
        }
        requestModelBuild()
    }
}