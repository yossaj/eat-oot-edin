package com.example.eatoutedinburgh.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("collection_id")
    @Expose
    val collectionId : Int,
    @SerializedName("image_url")
    @Expose
    val imageUrl : String,
    val title : String,
    val description : String
)