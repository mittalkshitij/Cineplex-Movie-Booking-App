package com.example.traningcomposeapp.home.data.network

import com.example.traningcomposeapp.home.data.model.PopularMoviesResponse
import com.example.traningcomposeapp.home.data.model.ScreeningAndUpcomingDTO
import retrofit2.http.GET

interface HomeAPI {

    @GET("movie/popular")
    suspend fun callApi(): PopularMoviesResponse

    @GET("movie/now_playing")
    suspend fun callNowPlayingApi(): ScreeningAndUpcomingDTO

    @GET("movie/upcoming")
    suspend fun callUpcomingApi(): ScreeningAndUpcomingDTO
}