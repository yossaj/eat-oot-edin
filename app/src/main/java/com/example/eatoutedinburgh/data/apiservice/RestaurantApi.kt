package com.example.eatoutedinburgh.data.apiservice

import android.util.JsonReader
import androidx.lifecycle.LiveData
import com.example.eatoutedinburgh.data.apiservice.responses.RestaurantListResponse
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.util.GenericApiResponse
import com.example.eatoutedinburgh.util.Keys.ZOMATO_API_KEY
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestaurantApi {

    @Headers(ZOMATO_API_KEY)
    @GET("api/v2.1/search?entity_id=76&entity_type=city")
    suspend fun search(@Query("q") query : String) :  RestaurantListResponse


}