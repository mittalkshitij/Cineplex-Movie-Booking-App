package com.example.traningcomposeapp.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traningcomposeapp.home.data.repository.HomeRepository
import com.example.traningcomposeapp.home.domain.model.ScreeningAndUpcomingResponse
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
    private val upcomingMovieUseCase: UpcomingMovieUseCase
) : ViewModel() {

    private val _nowPlayingResponse =
        MutableStateFlow<Result<ScreeningAndUpcomingResponse>>(Result.Loading)
    val nowPlayingResponse = _nowPlayingResponse.asStateFlow()

    private val _upcomingResponse =
        MutableStateFlow<Result<ScreeningAndUpcomingResponse>>(Result.Loading)
    val upcomingResponse = _upcomingResponse.asStateFlow()

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
}