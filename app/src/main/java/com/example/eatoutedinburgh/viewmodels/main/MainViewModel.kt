package com.example.eatoutedinburgh.viewmodels.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.eatoutedinburgh.data.MainRepository
import com.example.eatoutedinburgh.data.models.RestaurantList
import javax.inject.Singleton


class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle) : ViewModel(){

    val restaurantList : LiveData<List<RestaurantList>>
        get() = mainRepository.restaurantList


    fun searchForRestaurants(){
        mainRepository.searchForRestaurants()
    }



}