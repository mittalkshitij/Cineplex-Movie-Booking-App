package com.example.traningcomposeapp.launcher.data.network

import com.example.traningcomposeapp.launcher.data.model.ImagesConfiguration
import retrofit2.http.GET

interface MainApi {

    @GET("configuration")
    suspend fun getImagesConfiguration(): ImagesConfiguration
}