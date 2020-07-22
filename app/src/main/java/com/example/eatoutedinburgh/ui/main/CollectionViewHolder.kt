package com.example.eatoutedinburgh.ui.main

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.eatoutedinburgh.R
import com.example.eatoutedinburgh.data.models.Collection
import com.example.eatoutedinburgh.databinding.CollectionCardBinding
import com.squareup.picasso.Picasso

class CollectionViewHolder(val binding : CollectionCardBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(collection : Collection){
        binding.collection = collection
        binding.collectionThumbnail.scaleType = ImageView.ScaleType.CENTER_CROP
        if(collection.imageUrl.isNotBlank()){
            Picasso.get().load(collection.imageUrl).into(binding.collectionThumbnail)
        }else{
            Picasso.get().load(R.drawable.fork).into(binding.collectionThumbnail)
        }

    }


}