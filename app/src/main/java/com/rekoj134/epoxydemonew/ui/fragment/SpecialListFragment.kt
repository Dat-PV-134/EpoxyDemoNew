package com.rekoj134.epoxydemonew.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.rekoj134.epoxydemonew.R
import com.rekoj134.epoxydemonew.databinding.FragmentSpecialListBinding

class SpecialListFragment : Fragment(R.layout.fragment_special_list) {
    private lateinit var binding: FragmentSpecialListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSpecialListBinding.bind(view)
        Glide.with(view.context)
            .load(Uri.parse("file:///android_asset/special/special.png"))
            .error(Uri.parse("file:///android_asset/background/50.webp")).into(binding.imgNoHope)
    }
}