package com.example.traningcomposeapp.home.data.network

import com.example.traningcomposeapp.home.data.model.PopularMoviesResponse
import retrofit2.http.GET

interface HomeAPI {

    @GET("movie/popular")
    suspend fun callApi() : PopularMoviesResponse
}