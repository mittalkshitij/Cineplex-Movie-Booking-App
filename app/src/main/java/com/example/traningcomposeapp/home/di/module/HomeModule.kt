package com.example.traningcomposeapp.home.di.module

import com.example.traningcomposeapp.home.data.network.HomeAPI
import com.example.traningcomposeapp.home.data.repository.HomeRepository
import com.example.traningcomposeapp.home.data.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

    companion object {
        @Provides
        fun providesHomeApi(retrofit: Retrofit): HomeAPI {
            return retrofit.create(HomeAPI::class.java)
        }
    }

    @Binds
    abstract fun providesHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

}