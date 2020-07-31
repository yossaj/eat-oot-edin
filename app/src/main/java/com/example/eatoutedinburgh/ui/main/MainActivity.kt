package com.example.eatoutedinburgh.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.eatoutedinburgh.R
import com.example.eatoutedinburgh.viewmodels.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if(viewModel.onBackPressedSwitch){
            viewModel._triggerListRest.postValue(true)
        }else{
            super.onBackPressed()
        }
    }
}