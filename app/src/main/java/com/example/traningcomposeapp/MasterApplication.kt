package com.example.traningcomposeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import javax.inject.Inject

@HiltAndroidApp
class MasterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}