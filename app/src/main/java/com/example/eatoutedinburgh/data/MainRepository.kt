package com.example.eatoutedinburgh.data

import androidx.lifecycle.MutableLiveData
import com.example.eatoutedinburgh.data.apiservice.RestaurantApi
import com.example.eatoutedinburgh.data.models.Restaurant
import kotlinx.coroutines.flow.flow
import org.intellij.lang.annotations.Flow

class MainRepository constructor( private val restaurantApi: RestaurantApi) {

    val restaurantList = MutableLiveData<List<Restaurant>>()

    fun searchForRestaurants(){
        restaurantApi.search("pizza")
    }


}