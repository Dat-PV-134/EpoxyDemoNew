package com.rekoj134.epoxydemonew.ui.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.rekoj134.epoxydemonew.ui.fragment.VerticalListFragment
import com.rekoj134.epoxydemonew.R
import com.rekoj134.epoxydemonew.databinding.ActivityMainBinding
import com.rekoj134.epoxydemonew.ui.fragment.HorizontalListFragment
import com.rekoj134.epoxydemonew.ui.fragment.ExpandableListFragment
import com.rekoj134.epoxydemonew.ui.fragment.TwoTypeList1Fragment
import com.rekoj134.epoxydemonew.ui.fragment.TwoTypeList2Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        handleEvent()
    }

    private fun handleEvent() {
        binding.btnVerticalList.setOnClickListener {
            replaceFragment(VerticalListFragment.newInstance())
        }

        binding.btnHorizontalList.setOnClickListener {
            replaceFragment(HorizontalListFragment.newInstance())
        }

        binding.btnTwoTypeList1.setOnClickListener {
            replaceFragment(TwoTypeList1Fragment.newInstance())
        }

        binding.btnTwoTypeList2.setOnClickListener {
            replaceFragment(TwoTypeList2Fragment.newInstance())
        }

        binding.btnSpecicalList.setOnClickListener {
            replaceFragment(ExpandableListFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}

@BindingAdapter("bind:imageUrl", "bind:isSelected")
fun loadImage(view: ImageView, url: String?, isSelected: Boolean) {
    Log.e("ANCUTKO", isSelected.toString())
    Glide.with(view.context)
        .load(if (!isSelected) Uri.parse("file:///android_asset/" + url) else Uri.parse("file:///android_asset/background/50.webp"))
        .error(Uri.parse("file:///android_asset/background/50.webp")).into(view)
}