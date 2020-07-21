package com.example.eatoutedinburgh.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RestaurantList(
    @SerializedName("restaurant")
    @Expose
    val restaurant : Restaurant
)