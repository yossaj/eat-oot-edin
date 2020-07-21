package com.example.eatoutedinburgh.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.eatoutedinburgh.data.apiservice.RestaurantApi
import com.example.eatoutedinburgh.data.models.RestaurantList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainRepository constructor( private val restaurantApi: RestaurantApi) {

    val restaurantList = MutableLiveData<List<RestaurantList>>()

    fun searchForRestaurants(){
        GlobalScope.launch {
            try {
                val restaurantsResponse = restaurantApi.search("pizza")
                restaurantList.postValue(restaurantsResponse.restaurants)

            }catch (e : HttpException){
                Log.e("Retrofit", "Retrofit error: ${e.message()}", e)
            }

        }
    }

    fun checkData(){

    }


}