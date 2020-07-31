package com.example.eatoutedinburgh.viewmodels.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.eatoutedinburgh.data.MainRepository
import com.example.eatoutedinburgh.data.models.Collection
import com.example.eatoutedinburgh.data.models.Restaurant

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle) : ViewModel(){

    val restaurants : LiveData<List<Restaurant>>
        get() = mainRepository.restaurants

    val _restaurantDetail = MutableLiveData<Restaurant>()
    val restaurantDetail : LiveData<Restaurant>
        get() = _restaurantDetail

    val collections : LiveData<List<Collection>>
        get() =  mainRepository.collections

    fun searchForRestaurants(q : String){
        mainRepository.searchForRestaurants(q)
    }

    var onBackPressedSwitch = false
    val _triggerListRest = MutableLiveData<Boolean>()
    val triggerListRest : LiveData<Boolean>
        get() = _triggerListRest


    fun searchForRestaurantById(q : String){
        mainRepository.searchForRestaurantById(q)
    }

    fun loadCollections(){
        mainRepository.loadCollections()
    }

    fun clearResutaurantList(){
        mainRepository.clearRestaurantList()
    }


    init {
        loadCollections()
    }



}