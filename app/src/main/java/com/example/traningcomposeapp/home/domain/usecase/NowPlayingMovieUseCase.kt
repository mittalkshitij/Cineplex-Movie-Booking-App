package com.example.traningcomposeapp.home.domain.usecase

import com.example.traningcomposeapp.common.domain.UseCase
import com.example.traningcomposeapp.home.data.repository.HomeRepository
import com.example.traningcomposeapp.home.domain.mapper.ScreeningAndUpcomingMapper
import com.example.traningcomposeapp.home.domain.model.ScreeningAndUpcomingResponse
import com.example.traningcomposeapp.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NowPlayingMovieUseCase @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher,
    private val repository: HomeRepository
) : UseCase<Unit, ScreeningAndUpcomingResponse>(coroutineDispatcher) {
    override suspend fun execute(parameters: Unit): Flow<Result<ScreeningAndUpcomingResponse>> =
        flow {
            try {
                val response = repository.callNowPlayingApi()
                val nowPlayingResponse = ScreeningAndUpcomingMapper().mapFrom(response)
                emit(Result.Success(nowPlayingResponse))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e.message.toString()))
            }
        }
}