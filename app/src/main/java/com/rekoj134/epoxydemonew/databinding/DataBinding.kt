package com.rekoj134.epoxydemonew.databinding

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:imageUrl", "bind:isSelected")
fun loadImage(view: ImageView, url: String?, isSelected: Boolean) {
    Log.e("ANCUTKO", isSelected.toString())
    Glide.with(view.context)
        .load(if (!isSelected) Uri.parse("file:///android_asset/" + url) else Uri.parse("file:///android_asset/background/50.webp"))
        .error(Uri.parse("file:///android_asset/background/50.webp")).into(view)
}
