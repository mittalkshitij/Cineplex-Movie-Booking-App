package com.example.traningcomposeapp.launcher.data.repository

import com.example.traningcomposeapp.launcher.data.model.ImagesConfiguration
import com.example.traningcomposeapp.launcher.data.network.MainApi
import javax.inject.Inject

interface MainRepository {
    suspend fun callImagesConfiguration(): ImagesConfiguration
}

class MainRepositoryImpl @Inject constructor(
    private val mainApi: MainApi
) : MainRepository {

    override suspend fun callImagesConfiguration(): ImagesConfiguration {
        return mainApi.getImagesConfiguration()
    }
}