package com.example.eatoutedinburgh.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.eatoutedinburgh.data.apiservice.RestaurantApi
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.data.models.RestaurantList
import kotlinx.coroutines.*
import retrofit2.HttpException

class MainRepository constructor( private val restaurantApi: RestaurantApi) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val restaurantList = MutableLiveData<List<RestaurantList>>()
    val restaurants : LiveData<List<Restaurant>>  =
        Transformations.map(restaurantList){ restaurantList ->
            restaurantList.map { it.restaurant }
        }

    fun searchForRestaurants(q : String ){
        uiScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val restaurantsResponse = restaurantApi.search(q)
                    restaurantList.postValue(restaurantsResponse.restaurants)
                }catch (e : HttpException){
                    Log.e("Retrofit", "Retrofit error: ${e.message()}", e)
                }catch (e : Throwable){
                    Log.e("Retrofit", "Retrofit error: ${e.message}", e)
                }
            }
        }
    }

}