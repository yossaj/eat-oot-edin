package com.example.eatoutedinburgh.ui.main

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.eatoutedinburgh.R
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.databinding.RestaurantItemCardsBinding
import com.squareup.picasso.Picasso

class RestaurantViewHolder(val binding : RestaurantItemCardsBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(restaurant : Restaurant){
        binding.restaurant = restaurant
        if(restaurant.thumbnail.isNotBlank() && restaurant.id > 0){
            binding.restaurantThumbnail.scaleType = ImageView.ScaleType.CENTER_CROP
            Picasso.get().load(restaurant.featuredImage).into(binding.restaurantThumbnail)
            binding.restaurantRating.setRating(restaurant.userRating?.aggregateRating!!.toFloat())
        }else if(restaurant.id < 0){
            binding.restaurantThumbnail.scaleType = ImageView.ScaleType.CENTER_CROP
            val drawable = restaurant.thumbnail
            Picasso.get().load(drawable).into(binding.restaurantThumbnail)
            binding.restaurantArea.text = restaurant.url
            binding.restaurantRating.visibility = View.GONE
        }
        else{
            binding.restaurantThumbnail.scaleType = ImageView.ScaleType.CENTER
            Picasso.get().load(R.drawable.fork).into(binding.restaurantThumbnail)
        }
    }


}