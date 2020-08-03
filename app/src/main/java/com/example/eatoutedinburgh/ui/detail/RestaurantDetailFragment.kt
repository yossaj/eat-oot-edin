package com.example.eatoutedinburgh.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.eatoutedinburgh.databinding.FragmentRestaurantDetailBinding
import com.example.eatoutedinburgh.viewmodels.main.MainViewModel
import com.squareup.picasso.Picasso


class RestaurantDetailFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRestaurantDetailBinding.inflate(inflater)
        viewModel.restaurantDetail.observe(viewLifecycleOwner, Observer {
           binding.restaurant = it
            it.location?.let {
                val location = it
                binding.directionsButton.setEnabled(true)
                binding.directionsButton.setOnClickListener{
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/?api=1&query=${location.address}")
                    )
                    startActivity(intent)
                }

            }


            if(it.thumbnail.isNotBlank()){
                Picasso.get().load(it.featuredImage).into(binding.detailRestaurantImage)
            }


        })



        return binding.root
    }




}