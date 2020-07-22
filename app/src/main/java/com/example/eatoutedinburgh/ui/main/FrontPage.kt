package com.example.eatoutedinburgh.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.eatoutedinburgh.R
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.databinding.FragmentFrontPageBinding
import com.example.eatoutedinburgh.viewmodels.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrontPage : Fragment() {

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
        val adapter = FrontPageRVAdapter()
        binding.frontPageRecycView.adapter = adapter
        binding.searchBox.setOnClickListener {
            val query = binding.textInputLayout.editText.toString()
            viewModel.searchForRestaurants(query)
        }
        viewModel.restaurants.observe(viewLifecycleOwner, Observer { restaurants ->
            adapter.submitList(restaurants)
        })

        return binding.root
    }

}