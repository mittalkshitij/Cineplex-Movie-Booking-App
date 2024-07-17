package com.example.traningcomposeapp.home.domain.model

data class ScreeningAndUpcomingResponse(
    val movieResults: List<MovieResults> = emptyList()
)

data class MovieResults(
    val backdropPath: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)