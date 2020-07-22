package com.example.eatoutedinburgh.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

        return binding.root
    }

}