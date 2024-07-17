package com.example.traningcomposeapp.home.domain.mapper

import com.example.traningcomposeapp.common.domain.Mapper
import com.example.traningcomposeapp.home.data.model.MovieResultsDTO
import com.example.traningcomposeapp.home.data.model.ScreeningAndUpcomingDTO
import com.example.traningcomposeapp.home.domain.model.MovieResults
import com.example.traningcomposeapp.home.domain.model.ScreeningAndUpcomingResponse
import com.example.traningcomposeapp.utils.Constants.EMPTY

class ScreeningAndUpcomingMapper : Mapper<ScreeningAndUpcomingDTO, ScreeningAndUpcomingResponse> {
    override fun mapFrom(from: ScreeningAndUpcomingDTO): ScreeningAndUpcomingResponse {
        return ScreeningAndUpcomingResponse(
            movieResults = from.movieResults?.map { MovieResultsMapper().mapFrom(it) }
                ?: emptyList()
        )
    }
}

class MovieResultsMapper : Mapper<MovieResultsDTO, MovieResults> {
    override fun mapFrom(from: MovieResultsDTO): MovieResults {
        return MovieResults(
            backdropPath = from.backdropPath ?: EMPTY,
            id = from.id ?: 0,
            originalLanguage = from.originalLanguage ?: EMPTY,
            originalTitle = from.originalTitle ?: EMPTY,
            overview = from.overview ?: EMPTY,
            popularity = from.popularity ?: 0.0,
            posterPath = from.posterPath ?: EMPTY,
            releaseDate = from.releaseDate ?: EMPTY,
            title = from.title ?: EMPTY,
            voteAverage = from.voteAverage ?: 0.0,
            voteCount = from.voteCount ?: 0
        )
    }
}