package com.rekoj134.epoxydemonew.epoxy.controller

import android.util.Log
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.rekoj134.epoxydemonew.itemImage
import com.rekoj134.epoxydemonew.itemLoadingFull
import com.rekoj134.epoxydemonew.itemLoadingItem
import com.rekoj134.epoxydemonew.model.Image

class VerticalListController : EpoxyController() {
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
            itemImage {
                id(item.id)
                url(item.src)
                onClick { _ ->
                    if (this@VerticalListController.setImageSelected.contains(item.id)) this@VerticalListController.setImageSelected.remove(item.id)
                    else this@VerticalListController.setImageSelected.add(item.id)
                    this@VerticalListController.requestModelBuild()
                }
                isSelected(this@VerticalListController.setImageSelected.contains(item.id))
                // If you want to make it contain full space of grid layout line. Just override span size
//                spanSizeOverride { totalSpanCount, position, itemCount ->
//                    totalSpanCount
//                }
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
}
