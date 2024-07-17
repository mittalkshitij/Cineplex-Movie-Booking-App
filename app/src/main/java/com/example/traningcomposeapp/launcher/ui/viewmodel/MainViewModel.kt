package com.example.traningcomposeapp.launcher.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traningcomposeapp.launcher.data.model.ImagesConfiguration
import com.example.traningcomposeapp.launcher.data.repository.MainRepository
import com.example.traningcomposeapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _imageConfigurationResponse =
        MutableStateFlow<Result<ImagesConfiguration>>(Result.Loading)
    val imageConfigurationResponse = _imageConfigurationResponse.asStateFlow()

    suspend fun callImagesConfiguration() {
        flow<Result<ImagesConfiguration>> {
            emit(Result.Success(mainRepository.callImagesConfiguration()))
        }.flowOn(coroutineDispatcher).stateIn(viewModelScope).collect {
            _imageConfigurationResponse.value = it
        }
    }
}