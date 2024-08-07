package com.example.traningcomposeapp.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traningcomposeapp.home.data.model.CinemaDetails
import com.example.traningcomposeapp.home.data.model.MovieBookingDetails
import com.example.traningcomposeapp.home.data.model.TicketCollectionDetails
import com.example.traningcomposeapp.home.data.repository.HomeRepository
import com.example.traningcomposeapp.home.domain.model.CreditsResponse
import com.example.traningcomposeapp.home.domain.model.MovieResults
import com.example.traningcomposeapp.home.domain.model.ScreeningAndUpcomingResponse
import com.example.traningcomposeapp.home.domain.usecase.MovieCreditsUseCase
import com.example.traningcomposeapp.home.domain.usecase.NowPlayingMovieUseCase
import com.example.traningcomposeapp.home.domain.usecase.UpcomingMovieUseCase
import com.example.traningcomposeapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val nowPlayingMovieUseCase: NowPlayingMovieUseCase,
    private val upcomingMovieUseCase: UpcomingMovieUseCase,
    private val movieCreditsUseCase: MovieCreditsUseCase
) : ViewModel() {

    private val _nowPlayingResponse =
        MutableStateFlow<Result<ScreeningAndUpcomingResponse>>(Result.Loading)
    val nowPlayingResponse = _nowPlayingResponse.asStateFlow()

    private val _upcomingResponse =
        MutableStateFlow<Result<ScreeningAndUpcomingResponse>>(Result.Loading)
    val upcomingResponse = _upcomingResponse.asStateFlow()

    private val _movieResult = MutableStateFlow<MovieResults?>(value = null)
    val movieResults = _movieResult.asStateFlow()

    private val _creditsResponse = MutableStateFlow<Result<CreditsResponse>>(Result.Loading)
    val creditsResponse = _creditsResponse.asStateFlow()

    private val _selectedCinema = MutableStateFlow<CinemaDetails?>(value = null)
    val selectedCinema = _selectedCinema.asStateFlow()

    private val _movieBookingDetails = MutableStateFlow<MovieBookingDetails?>(value = null)
    val movieBookingDetails = _movieBookingDetails.asStateFlow()

    private val _ticketCollectionDetails =
        MutableStateFlow<MutableList<TicketCollectionDetails>>(mutableListOf())
    val ticketCollectionDetails = _ticketCollectionDetails.asStateFlow()


    suspend fun callApi() {
        homeRepository.callApi()
    }

    suspend fun callNowPlayingApi() {
        nowPlayingMovieUseCase(Unit).stateIn(viewModelScope).collect {
            _nowPlayingResponse.value = it
        }
    }

    suspend fun callUpcomingApi() {
        upcomingMovieUseCase(Unit).stateIn(viewModelScope).collect {
            _upcomingResponse.value = it
        }
    }

    suspend fun callCreditsApi(movieId: Int) {
        movieCreditsUseCase(movieId).stateIn(viewModelScope).collect {
            _creditsResponse.value = it
        }
    }

    fun setMovieDetails(movieDetails: MovieResults) {
        _movieResult.value = movieDetails
    }

    fun setSelectedCinemaDetails(selectedCinemaDetail: CinemaDetails?) {
        _selectedCinema.value = selectedCinemaDetail
    }

    fun setMovieBookingDetails(movieBookingDetails: MovieBookingDetails) {
        _movieBookingDetails.value = movieBookingDetails
    }

    fun setTicketCollectionDetails(ticketCollectionDetails: TicketCollectionDetails) {
        _ticketCollectionDetails.value.add(ticketCollectionDetails)
        println(_ticketCollectionDetails.value)
    }
}