package com.example.eatoutedinburgh.data.apiservice.responses

import com.example.eatoutedinburgh.data.models.RestaurantList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RestaurantListResponse(
    @SerializedName("results_found")
    @Expose
    val count : Int,

    @SerializedName("restaurants")
    @Expose
    val restaurants : List<RestaurantList>
)