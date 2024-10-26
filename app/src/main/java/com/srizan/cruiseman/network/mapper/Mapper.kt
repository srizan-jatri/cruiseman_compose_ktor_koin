package com.srizan.cruiseman.network.mapper


import com.srizan.cruiseman.domain.entity.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface Mapper<R, E> {
    fun mapFromApiResponse(type: R): E
}

fun <R, E> mapFromApiResponse(
    apiResult: Flow<ApiResult<R>>,
    mapper: Mapper<R, E>
): Flow<ApiResult<E>> {
    return apiResult.map {
        when (it) {
            is ApiResult.SuccessState -> ApiResult.SuccessState(mapper.mapFromApiResponse(it.data))
            is ApiResult.ErrorState -> ApiResult.ErrorState(it.errorResponse)
            is ApiResult.LoadingState -> ApiResult.LoadingState
        }
    }
}