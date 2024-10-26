package com.srizan.cruiseman.domain.entity

import com.srizan.cruiseman.network.model.ErrorResponse

sealed interface ApiResult<out T> {
    data object LoadingState : ApiResult<Nothing>
    data class SuccessState<T>(val data: T) : ApiResult<T>
    data class ErrorState(val errorResponse: ErrorResponse) : ApiResult<Nothing>
}