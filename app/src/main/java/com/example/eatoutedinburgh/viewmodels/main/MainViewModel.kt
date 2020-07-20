package com.example.eatoutedinburgh.viewmodels.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.eatoutedinburgh.data.MainRepository
import com.example.eatoutedinburgh.data.models.Restaurant
import kotlinx.coroutines.flow.flow
import org.intellij.lang.annotations.Flow


class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle) : ViewModel(){

    fun searchForRestaurants(){
        mainRepository.searchForRestaurants()
    }



}