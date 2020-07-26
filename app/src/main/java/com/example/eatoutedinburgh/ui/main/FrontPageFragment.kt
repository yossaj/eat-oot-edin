package com.example.eatoutedinburgh.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.eatoutedinburgh.R
import com.example.eatoutedinburgh.databinding.FragmentFrontPageBinding
import com.example.eatoutedinburgh.viewmodels.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrontPageFragment : Fragment() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

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
        binding.searchBox.setOnKeyListener { v, keyCode, event ->
            val query = binding.textInputLayout.editText!!.text.toString()
            if(query.length >= 3){
                viewModel.searchForRestaurants(query)
                true
            }
            false}
        viewModel.restaurants.observe(viewLifecycleOwner, Observer { restaurants ->
           restaurantAdapter.submitList(restaurants)
        })
        shrinkRecyclerViewOnScroll(binding)
        return binding.root
    }

    fun shrinkRecyclerViewOnScroll(binding: FragmentFrontPageBinding){

        val height = binding.collectionRecyclerView.height.toFloat()
        val moveUp = ObjectAnimator.ofFloat(binding.collectionRecyclerView, View.TRANSLATION_Y, -height)
        var fadeOut = ObjectAnimator.ofArgb(binding.collectionRecyclerView,
            "backgroundColor", Color.WHITE, Color.TRANSPARENT)
//                    animator.repeatCount = 1
//                    animator.repeatMode = ObjectAnimator.REVERSE
//                    animator.disableViewDuringAnimation(translateButton)
        val set = AnimatorSet()
        set.playTogether(moveUp, fadeOut)
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                binding.collectionRecyclerView.visibility = View.GONE

            }
        })

        binding.restaurantRecyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy > 1){
                    set.start()
                }else if(dy < -1){
                    binding.collectionRecyclerView.visibility = View.VISIBLE
                    moveUp.repeatCount = 1
                    moveUp.repeatMode = ObjectAnimator.REVERSE
                    fadeOut.repeatCount = 1
                    fadeOut.repeatCount = ObjectAnimator.REVERSE
                    set.start()
                }
            }

        })
    }



}