package com.example.traningcomposeapp.home.domain.usecase

import com.example.traningcomposeapp.common.domain.UseCase
import com.example.traningcomposeapp.home.data.repository.HomeRepository
import com.example.traningcomposeapp.home.domain.mapper.CreditsResponseMapper
import com.example.traningcomposeapp.home.domain.model.CreditsResponse
import com.example.traningcomposeapp.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieCreditsUseCase @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher,
    private val repository: HomeRepository
) : UseCase<Int, CreditsResponse>(coroutineDispatcher) {
    override suspend fun execute(parameters: Int): Flow<Result<CreditsResponse>> =
        flow {
            try {
                val response = repository.callCreditsApi(parameters)
                val creditResponse = CreditsResponseMapper().mapFrom(response)
                emit(Result.Success(creditResponse))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e.message.toString()))
            }
        }
}