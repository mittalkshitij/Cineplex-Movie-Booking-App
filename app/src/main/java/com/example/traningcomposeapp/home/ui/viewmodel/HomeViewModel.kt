package com.example.traningcomposeapp.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.traningcomposeapp.home.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    suspend fun callApi() {
        homeRepository.callApi()
    }
}