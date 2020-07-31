package com.example.eatoutedinburgh.ui.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.eatoutedinburgh.R
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.databinding.FragmentFrontPageBinding
import com.example.eatoutedinburgh.viewmodels.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrontPageFragment : Fragment() {

    private var categoryItemClickedCheck = false

    private val viewModel : MainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFrontPageBinding.inflate(inflater)
        collectionRecyclerViewSetup(binding)
        restaurantRecyclerViewSetup(binding)
        return binding.root
    }

    private fun restaurantRecyclerViewSetup(binding: FragmentFrontPageBinding) {
        val restaurantAdapter = RestaurantAdapter(RestaurantAdapter.OnClickListener {
            val restaurant = it
                if (categoryItemClickedCheck){
                    viewModel._restaurantDetail.postValue(restaurant)
                    this.findNavController().navigate(FrontPageFragmentDirections.actionFrontPageToRestaurantDetailFragment())
                    viewModel.onBackPressedSwitch = false
                    categoryItemClickedCheck = false
                    viewModel.clearResutaurantList()
                }else{
                    val searchTerm = restaurant.name
                    viewModel.searchForRestaurants(searchTerm)
                    categoryItemClickedCheck = true
                    viewModel.onBackPressedSwitch = true
                    binding.restaurantRcProgress.visibility = View.VISIBLE
                }

        })
        binding.restaurantRecyclerView.adapter = restaurantAdapter
        restaurantAdapter.submitList(categoriesList())
        binding.searchBox.setOnKeyListener { v, keyCode, event ->
            val query = binding.textInputLayout.editText!!.text.toString()
            if (query.length >= 3) {
                viewModel.searchForRestaurants(query)
                categoryItemClickedCheck = true
                viewModel.onBackPressedSwitch = true
            } else {
                resetRestaurantRecyclerView(restaurantAdapter, binding)
            }
            false
        }

        viewModel.restaurants.observe(viewLifecycleOwner, Observer { restaurants ->
            if (restaurants.size > 0 && categoryItemClickedCheck) {
                restaurantAdapter.submitList(restaurants)
            }
        })

        viewModel.triggerListRest.observe(viewLifecycleOwner, Observer {
            if(it){
                resetRestaurantRecyclerView(restaurantAdapter, binding)
            }
        })

        shrinkRecyclerViewOnScroll(binding)
    }

    private fun resetRestaurantRecyclerView(
        restaurantAdapter: RestaurantAdapter,
        binding: FragmentFrontPageBinding
    ) {
        viewModel.clearResutaurantList()
        restaurantAdapter.submitList(categoriesList())
        binding.restaurantRcProgress.visibility = View.GONE
        categoryItemClickedCheck = false
        viewModel.onBackPressedSwitch = false
    }


    private fun collectionRecyclerViewSetup(binding: FragmentFrontPageBinding) {
        val collectionAdapter = CollectionAdapter(CollectionAdapter.OnClickListener{
            this.findNavController().navigate(FrontPageFragmentDirections.actionFrontPageToWebviewFragment(it))
        })
        binding.collectionRecyclerView.adapter = collectionAdapter
        viewModel.collections.observe(viewLifecycleOwner, Observer {
            collectionAdapter.submitList(it)
        })
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
        val coffeeItem = Restaurant(-1, "Coffee","Find a local brew!", "10",  null, null, null, coffee , null)
        val breakfast = prepareDrawable(R.drawable.breakfast)
        val breakfastItem = Restaurant(-1, "Breakfast", "Breakfast places near you", "10",null, null, null, breakfast, null)
        return listOf(coffeeItem, breakfastItem)
    }

    fun prepareDrawable(drawable : Int) : String{
        return Uri.parse("android.resource://com.example.eatoutedinburgh/" + drawable).toString()
    }



}