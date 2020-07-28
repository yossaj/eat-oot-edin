package com.example.eatoutedinburgh.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.eatoutedinburgh.data.apiservice.RestaurantApi
import com.example.eatoutedinburgh.data.models.Collection
import com.example.eatoutedinburgh.data.models.CollectionList
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.data.models.RestaurantList
import kotlinx.coroutines.*
import retrofit2.HttpException

class MainRepository constructor( private val restaurantApi: RestaurantApi) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val restaurantList = MutableLiveData<List<RestaurantList>>()
    val restaurants : LiveData<List<Restaurant>>  =
        Transformations.map(restaurantList){ _restaurantList ->
            _restaurantList.map { it.restaurant }
        }

    val collectionList = MutableLiveData<List<CollectionList>>()
    val collections : LiveData<List<Collection>> =
        Transformations.map(collectionList){ _collectionList ->
            _collectionList.map{ it.collection }
        }



    fun searchForRestaurants(q : String ){
        uiScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val restaurantsResponse = restaurantApi.search(q)
                    Log.d("Request Query", q)
                    Log.d("Request", restaurantsResponse.toString())
                    restaurantList.postValue(restaurantsResponse.restaurants)
                }catch (e : HttpException){
                    Log.e("Retrofit", "Retrofit error: ${e.message()}", e)
                }catch (e : Throwable){
                    Log.e("Retrofit", "Retrofit error: ${e.message}", e)
                }
            }
        }
    }

    fun searchForRestaurantById(q : String){
        uiScope.launch {
            withContext(Dispatchers.IO){
                try {
                    restaurantApi.getRestaurantById(q)
                }catch (e : Throwable){

                }catch (e : HttpException){

                }
            }

        }

    }

    fun loadCollections(){
        uiScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val collection = restaurantApi.getCollection()
                    collectionList.postValue(collection.collections)
                }catch (e : HttpException){
                    Log.e("Retrofit", "Retrofit error: ${e.message()}", e)
                }catch (e : Throwable){
                    Log.e("Retrofit", "Retrofit error: ${e.message}", e)
                }
            }
        }
    }

    fun clearRestaurantList(){
            restaurantList.postValue(emptyList())
    }

}