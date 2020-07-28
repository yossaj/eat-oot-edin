package com.example.eatoutedinburgh.viewmodels.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.eatoutedinburgh.data.MainRepository
import com.example.eatoutedinburgh.data.models.Collection
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.data.models.RestaurantList
import javax.inject.Singleton

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle) : ViewModel(){

    val restaurants : LiveData<List<Restaurant>>
        get() = mainRepository.restaurants

    val collections : LiveData<List<Collection>>
        get() =  mainRepository.collections

    fun searchForRestaurants(q : String){
        mainRepository.searchForRestaurants(q)
    }

    fun loadCollections(){
        mainRepository.loadCollections()
    }

    fun clearResutaurantList(){
        mainRepository.clearRestaurantList()
    }



}