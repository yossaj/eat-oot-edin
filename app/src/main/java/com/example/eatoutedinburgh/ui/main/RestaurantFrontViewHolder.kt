package com.example.eatoutedinburgh.ui.main

import android.graphics.Color
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.eatoutedinburgh.R
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.databinding.RestaurantItemCardBinding
import com.squareup.picasso.Picasso

class RestaurantFrontViewHolder(val binding : RestaurantItemCardBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(restaurant : Restaurant){
        binding.restaurant = restaurant
        binding.restaurantThumbnail.scaleType = ImageView.ScaleType.CENTER_CROP
        if( !restaurant.thumbnail.isNullOrBlank()){
            Picasso.get().load(restaurant.thumbnail).into(binding.restaurantThumbnail)
        }else{
            binding.restaurantThumbnail.scaleType = ImageView.ScaleType.CENTER
            binding.restaurantThumbnail.setBackgroundColor(Color.WHITE)
            Picasso.get().load(R.drawable.fork).into(binding.restaurantThumbnail)
        }

    }


}