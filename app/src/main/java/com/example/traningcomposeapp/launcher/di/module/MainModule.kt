package com.example.traningcomposeapp.launcher.di.module

import com.example.traningcomposeapp.launcher.data.network.MainApi
import com.example.traningcomposeapp.launcher.data.repository.MainRepository
import com.example.traningcomposeapp.launcher.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    companion object {
        @Provides
        fun providesMainApi(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }
    }

    @Binds
    abstract fun providesMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

}