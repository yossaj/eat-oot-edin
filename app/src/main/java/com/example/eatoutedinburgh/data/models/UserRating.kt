package com.example.eatoutedinburgh.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserRating(
    @SerializedName("aggregate_rating")
    @Expose
    val aggregateRating : String,
    @SerializedName("rating_text")
    @Expose
    val ratingText : String,
    @SerializedName("rating_color")
    @Expose
    val ratingColor : String,
    val votes : String
)