package com.rekoj134.epoxydemonew.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.airbnb.epoxy.Carousel
import com.rekoj134.epoxydemonew.R
import com.rekoj134.epoxydemonew.databinding.FragmentExpandableListBinding
import com.rekoj134.epoxydemonew.itemEpandableDetail
import com.rekoj134.epoxydemonew.itemExpandable

class ExpandableListFragment : Fragment(R.layout.fragment_expandable_list) {
    private lateinit var binding: FragmentExpandableListBinding
    private var listItem = listOf(0, 1, 2, 3, 4, 5)
    private var setOfItemExpanded = HashSet<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentExpandableListBinding.bind(view)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvExpandable.withModels {
            listItem.forEach {
                itemExpandable {
                    id(it)
                    isExpand(setOfItemExpanded.contains(it))
                    data(if (it % 2 == 0) arrayListOf("Good morning", "Good bye", "Hello world", "Xin chao")
                        else arrayListOf("Java", "Kotlin", "Dart", "Swift", "Python", "Leesin", "Yasou")
                    )
                    onClick { _ ->
                        if (setOfItemExpanded.contains(it)) setOfItemExpanded.remove(it)
                        else setOfItemExpanded.add(it)
                        this@withModels.requestModelBuild()
                    }
                }
            }
        }
    }
}

@BindingAdapter("bind:visibility")
fun setVisibility(view: Carousel, isExpand: Boolean) {
    view.visibility = if (isExpand) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:data")
fun setVisibility(view: Carousel, data: ArrayList<String>) {
    view.withModels {
        data.forEach {
            itemEpandableDetail {
                id(it.hashCode())
                content(it)
            }
        }
    }

    // Other way to build model
//    view.withModels {
//        data.forEach {
//            ItemEpandableDetailBindingModel_().id(it.hashCode()).content(it).addTo(this@withModels)
//        }
//    }
}
