package com.rekoj134.epoxydemonew.epoxy.controller

import android.util.Log
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.rekoj134.epoxydemonew.itemImage
import com.rekoj134.epoxydemonew.itemLoadingFull
import com.rekoj134.epoxydemonew.itemLoadingItem
import com.rekoj134.epoxydemonew.model.Image

class VerticalListController : AsyncEpoxyController() {
    var listImage: ArrayList<Image> = ArrayList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {

        if (listImage.isEmpty()) {
            itemLoadingFull {
                id("loading_full")
                spanSizeOverride { totalSpanCount, position, itemCount ->
                    totalSpanCount
                }
            }
            return
        }

        Log.e("ANCUTKO", listImage.toString())
        listImage.forEach { item ->
            itemImage {
                id(item.id)
                imageItem(item)
                onClick { _ ->
                    this@VerticalListController.onItemSelected(item)
                }
                spanSizeOverride { totalSpanCount, position, itemCount ->
//                    if (position == 4) totalSpanCount
//                    else 1
                    1
                }
            }
        }

        if (listImage.size < 13) {
            itemLoadingItem {
                id("loading_more")
                spanSizeOverride { totalSpanCount, position, itemCount ->
                    totalSpanCount
                }
            }
        }
    }

    private fun onItemSelected(imageSelected: Image) {
        val tempList = ArrayList<Image>()
        this@VerticalListController.listImage.forEach {
            if (it.id == imageSelected.id) {
                val tempItem = it.copy()
                tempItem.isSelected = !tempItem.isSelected
                tempList.add(tempItem)
            } else {
                tempList.add(it)
            }
        }
        Log.e("ANCUTKO", "temp " + tempList.toString())
        this@VerticalListController.listImage = tempList
    }
}
