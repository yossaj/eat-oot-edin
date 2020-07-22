package com.example.eatoutedinburgh.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.example.eatoutedinburgh.R
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.databinding.RestaurantItemCardsBinding
import com.squareup.picasso.Picasso

class RestaurantViewHolder(val binding : RestaurantItemCardsBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(restaurant : Restaurant){
        binding.restaurant = restaurant
        if(restaurant.thumbnail.isNotBlank()){
            Picasso.get().load(restaurant.thumbnail).into(binding.restaurantThumbnail)
        }else{
            Picasso.get().load(R.drawable.fork).into(binding.restaurantThumbnail)
        }
    }


}