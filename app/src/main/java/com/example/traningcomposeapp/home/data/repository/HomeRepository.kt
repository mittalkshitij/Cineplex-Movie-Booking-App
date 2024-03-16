package com.example.traningcomposeapp.home.data.repository

import com.example.traningcomposeapp.home.data.model.PopularMoviesResponse
import com.example.traningcomposeapp.home.data.network.HomeAPI
import javax.inject.Inject

interface HomeRepository {
    suspend fun callApi(): PopularMoviesResponse
}

class HomeRepositoryImpl @Inject constructor(
    private val homeAPI: HomeAPI
) : HomeRepository {
    override suspend fun callApi(): PopularMoviesResponse {
        return homeAPI.callApi()
    }
}