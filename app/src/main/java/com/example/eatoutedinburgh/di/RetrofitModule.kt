package com.example.eatoutedinburgh.di

import com.example.eatoutedinburgh.data.apiservice.RestaurantApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder() : Gson{
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson:  Gson) : Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://developers.zomato.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideRestaurantApi(retrofit: Retrofit.Builder) : RestaurantApi{
        return retrofit
            .build()
            .create(RestaurantApi::class.java)
    }
}