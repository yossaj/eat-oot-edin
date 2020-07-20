package com.example.eatoutedinburgh.data.apiservice

import com.example.eatoutedinburgh.data.models.Restaurant
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantApi {

    @GET("api/v2.1/search?entity_id=76&entity_type=city")
    fun search(@Query("q") query : String) : List<Restaurant>


}