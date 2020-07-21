package com.example.eatoutedinburgh.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.databinding.RestaurantItemCardBinding
import com.squareup.picasso.Picasso

class RestaurantFrontViewHolder(val binding : RestaurantItemCardBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(restaurant : Restaurant){
        binding.restaurant = restaurant
        if( !restaurant.thumbnail.isNullOrBlank()){
            Picasso.get().load(restaurant.thumbnail).into(binding.restaurantThumbnail)
        }

    }


}