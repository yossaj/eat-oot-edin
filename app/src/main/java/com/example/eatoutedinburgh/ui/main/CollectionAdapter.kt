package com.example.eatoutedinburgh.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.eatoutedinburgh.data.models.Collection
import com.example.eatoutedinburgh.databinding.CollectionCardBinding

class CollectionAdapter(val clickListener : OnClickListener) : ListAdapter<Collection, CollectionViewHolder>(CollectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CollectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CollectionCardBinding.inflate(layoutInflater, parent,false)
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder : CollectionViewHolder, position : Int) {
        val collection = getItem(position)
        holder.itemView.setOnClickListener{clickListener.onClick(collection.shareUrl)}
        holder.bind(collection)
    }

    class CollectionDiffCallback : DiffUtil.ItemCallback<Collection>(){
        override fun areItemsTheSame(oldItem : Collection, newItem : Collection): Boolean {
            return oldItem.collectionId == newItem.collectionId
        }

        override fun areContentsTheSame(oldItem : Collection, newItem : Collection): Boolean {
            return oldItem.equals(newItem)
        }
    }

    class OnClickListener(val clickListener: (url : String) -> Unit) {
        fun onClick(url: String) = clickListener(url)
    }

}