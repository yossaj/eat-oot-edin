package com.example.eatoutedinburgh.ui.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.eatoutedinburgh.R
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.databinding.FragmentFrontPageBinding
import com.example.eatoutedinburgh.viewmodels.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrontPageFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.e("HASH", viewModel.hashCode().toString())
        Log.d("Viewmodel Hash", viewModel.hashCode().toString())
        val binding = FragmentFrontPageBinding.inflate(inflater)
        val collectionAdapter = CollectionAdapter()
        viewModel.loadCollections()
        binding.collectionRecyclerView.adapter = collectionAdapter
        viewModel.collections.observe(viewLifecycleOwner, Observer {
            collectionAdapter.submitList(it)
        })

        val restaurantAdapter = RestaurantAdapter()
        binding.restaurantRecyclerView.adapter = restaurantAdapter
        restaurantAdapter.submitList(categoriesList())
        binding.searchBox.setOnKeyListener { v, keyCode, event ->
            val query = binding.textInputLayout.editText!!.text.toString()
            if(query.length >= 3){
                viewModel.searchForRestaurants(query)
                true
            }else{
                viewModel.clearResutaurantList()
                restaurantAdapter.submitList(categoriesList())
            }
            false}

        viewModel.restaurants.observe(viewLifecycleOwner, Observer { restaurants ->
            if(restaurants.size > 0){
                restaurantAdapter.submitList(restaurants)
            }


        })

        shrinkRecyclerViewOnScroll(binding)
        return binding.root
    }

    fun shrinkRecyclerViewOnScroll(binding: FragmentFrontPageBinding){

        val height = binding.collectionRecyclerView.height.toFloat()

        binding.restaurantRecyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var scrollUp = true
                if(dy > 0 && scrollUp){

                    val restaurantMoveUp = ObjectAnimator.ofFloat(binding.restaurantRecyclerView, "translationY", -300F)
                    restaurantMoveUp.duration = 100
                    restaurantMoveUp.interpolator = AccelerateDecelerateInterpolator()
                    val categoryMoveUp = ObjectAnimator.ofFloat(binding.collectionRecyclerView,"translationY", -300F)
                    categoryMoveUp.duration = 100
                    categoryMoveUp.interpolator = AccelerateDecelerateInterpolator()
                    val set = AnimatorSet()
                    set.playTogether(restaurantMoveUp, categoryMoveUp)
                    set.start()
                    scrollUp = false
                }else if(dy < -5){

                    val restaurantMoveUp = ObjectAnimator.ofFloat(binding.restaurantRecyclerView, "translationY", 0F)
                    restaurantMoveUp.duration = 200
                    restaurantMoveUp.interpolator = AccelerateDecelerateInterpolator()
                    val categoryMoveUp = ObjectAnimator.ofFloat(binding.collectionRecyclerView,"translationY", 0F)
                    categoryMoveUp.duration = 200
                    categoryMoveUp.interpolator = AccelerateDecelerateInterpolator()
                    val set = AnimatorSet()
                    set.playTogether(restaurantMoveUp, categoryMoveUp)
                    set.start()
                    scrollUp = true
                }
            }

        })
    }

    fun categoriesList() : List<Restaurant>{
        val coffee = prepareDrawable(R.drawable.coffee)
        val coffeeItem = Restaurant(-1, "Coffee","Find a local brew!", "10",  coffee)
        val breakfast = prepareDrawable(R.drawable.breakfast)
        val breakfastItem = Restaurant(-1, "Breakfast", "Breakfast places near you", "10", breakfast)
        return listOf(coffeeItem, breakfastItem)
    }

    fun prepareDrawable(drawable : Int) : String{
        return Uri.parse("android.resource://com.example.eatoutedinburgh/" + drawable).toString()
    }



}