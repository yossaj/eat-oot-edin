package com.example.eatoutedinburgh.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.databinding.RestaurantItemCardBinding

class FrontPageRVAdapter : ListAdapter<Restaurant, RestaurantFrontViewHolder>(RestaurantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RestaurantFrontViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RestaurantItemCardBinding.inflate(layoutInflater, parent,false)
        return RestaurantFrontViewHolder(binding)
    }

    override fun onBindViewHolder(holder : RestaurantFrontViewHolder, position : Int) {
        val restaurant = getItem(position)
        holder.bind(restaurant)
    }

    class RestaurantDiffCallback : DiffUtil.ItemCallback<Restaurant>(){
        override fun areItemsTheSame(oldItem : Restaurant, newItem : Restaurant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem : Restaurant, newItem : Restaurant): Boolean {
            return oldItem.equals(newItem)
        }
    }

}