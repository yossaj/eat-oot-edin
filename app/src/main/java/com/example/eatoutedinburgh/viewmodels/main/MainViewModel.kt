package com.example.eatoutedinburgh.viewmodels.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.eatoutedinburgh.data.MainRepository
import com.example.eatoutedinburgh.data.models.Restaurant
import com.example.eatoutedinburgh.data.models.Restaurants
import kotlinx.coroutines.flow.flow
import org.intellij.lang.annotations.Flow


class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle) : ViewModel(){

    val restaurantList : LiveData<List<Restaurants>>
        get() = mainRepository.restaurantList

    fun searchForRestaurants(){
        mainRepository.searchForRestaurants()
    }

    fun checkdata(){
        mainRepository.checkData()
    }



}