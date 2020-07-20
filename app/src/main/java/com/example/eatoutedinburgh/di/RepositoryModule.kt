package com.example.eatoutedinburgh.di

import com.example.eatoutedinburgh.data.MainRepository
import com.example.eatoutedinburgh.data.apiservice.RestaurantApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(restaurentApi : RestaurantApi) : MainRepository {
        return MainRepository(restaurentApi)
    }

}