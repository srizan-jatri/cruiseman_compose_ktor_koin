package com.srizan.cruiseman.network

import com.srizan.cruiseman.data.isSuccessful
import com.srizan.cruiseman.domain.entity.ApiResult
import com.srizan.cruiseman.network.model.ErrorResponse
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class NetworkBoundResource(val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend inline fun <reified R> downloadData(
        crossinline api: suspend () -> HttpResponse
    ): Flow<ApiResult<R>> {
        return withContext(dispatcher) {
            flow {
                emit(ApiResult.LoadingState)
                val httpResponse = api()
                if (httpResponse.isSuccessful()) {
                    emit(ApiResult.SuccessState(data = httpResponse.body<R>()))
                } else {
                    emit(ApiResult.ErrorState(httpResponse.body<ErrorResponse>()))
                }
            }.catch { throwable ->
                emit(ApiResult.ErrorState(ErrorResponse(message = throwable.toString())))
                throwable.printStackTrace()
            }
        }
    }
}