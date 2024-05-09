package com.rekoj134.epoxydemonew.epoxy.controller

import android.util.Log
import com.airbnb.epoxy.EpoxyController
import com.rekoj134.epoxydemonew.itemImageHorizontal
import com.rekoj134.epoxydemonew.itemLoadingFull
import com.rekoj134.epoxydemonew.itemLoadingItem
import com.rekoj134.epoxydemonew.model.Image

class HorizontalListController : EpoxyController() {
    var listImage: ArrayList<Image> = ArrayList()
        set(value) {
            field = value
            requestModelBuild()
        }

    private var setImageSelected = HashSet<Int>()

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
            itemImageHorizontal {
                id(item.id)
                onClick { _ ->
                    if (this@HorizontalListController.setImageSelected.contains(item.id)) this@HorizontalListController.setImageSelected.remove(item.id)
                    else this@HorizontalListController.setImageSelected.add(item.id)
                    this@HorizontalListController.requestModelBuild()
                }
                isSelected(this@HorizontalListController.setImageSelected.contains(item.id))
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
        this@HorizontalListController.listImage.forEach {
            if (it.id == imageSelected.id) {
                val tempItem = it.copy()
                tempItem.isSelected = !tempItem.isSelected
                tempList.add(tempItem)
            } else {
                tempList.add(it)
            }
        }
        Log.e("ANCUTKO", "temp " + tempList.toString())
        this@HorizontalListController.listImage = tempList
    }
}
