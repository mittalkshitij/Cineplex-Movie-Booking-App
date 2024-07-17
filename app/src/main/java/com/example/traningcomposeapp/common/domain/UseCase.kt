package com.example.traningcomposeapp.common.domain

import com.example.traningcomposeapp.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): Flow<Result<R>> =
        execute(parameters).flowOn(coroutineDispatcher)

    protected abstract suspend fun execute(parameters: P): Flow<Result<R>>
}