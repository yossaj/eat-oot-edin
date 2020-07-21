package com.example.eatoutedinburgh.data.apiservice.responses

import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.data.models.Restaurants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("restaurant")
    @Expose
    var restaurant : Restaurant
)