package com.example.eatoutedinburgh.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.eatoutedinburgh.R
import com.example.eatoutedinburgh.viewmodels.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.searchForRestaurants()
        setContentView(R.layout.activity_main)
        viewModel.restaurantList.observe(this, Observer {
            it?.let {
                for (restaurant in it) {
                    Log.d("Response", restaurant.toString())
                }
            }
        })

    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        return super.onCreateView(parent, name, context, attrs)

    }
}