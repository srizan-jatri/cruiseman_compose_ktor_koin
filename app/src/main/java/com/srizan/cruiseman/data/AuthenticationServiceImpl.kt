package com.srizan.cruiseman.data

import com.srizan.cruiseman.data.sharedpref.Prefs
import com.srizan.cruiseman.domain.entity.ApiResult
import com.srizan.cruiseman.domain.entity.LoginApiEntity
import com.srizan.cruiseman.domain.entity.ProfileApiEntity
import com.srizan.cruiseman.domain.repository.AuthenticationService
import com.srizan.cruiseman.network.NetworkBoundResource
import com.srizan.cruiseman.network.mapper.LoginApiMapper
import com.srizan.cruiseman.network.mapper.ProfileApiMapper
import com.srizan.cruiseman.network.mapper.mapFromApiResponse
import com.srizan.cruiseman.network.model.LoginRequestBody
import com.srizan.cruiseman.network.model.LogoutApiResponse
import com.srizan.cruiseman.network.model.RefreshTokenApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.parameters
import kotlinx.coroutines.flow.Flow



const val URL_LOGIN = "/v1/auth/login"
const val URL_LOGOUT = "/v1/auth/logout"
const val URL_PROFILE = "/v1/auth/profile"
const val URL_TOKEN_REFRESH = "/v1/auth/refresh"

class AuthenticationServiceImpl(
    private val client: HttpClient,
    private val networkBoundResource: NetworkBoundResource
) : AuthenticationService {

    override suspend fun login(phone: String, password: String): Flow<ApiResult<LoginApiEntity>> {
        return mapFromApiResponse(
            apiResult = networkBoundResource.downloadData {
                client.post(URL_LOGIN) {
                    contentType(ContentType.Application.Json)
                    setBody(LoginRequestBody(phone, password, "123456"))
                }
            },
            mapper = LoginApiMapper()
        )
    }

    override suspend fun logout(): Flow<ApiResult<LogoutApiResponse>> {
        return networkBoundResource.downloadData { client.patch(URL_LOGOUT) }
    }

    override suspend fun getProfile(): Flow<ApiResult<ProfileApiEntity>> {
        return mapFromApiResponse(
            apiResult = networkBoundResource.downloadData { client.get(URL_PROFILE) },
            mapper = ProfileApiMapper()
        )
    }

    override suspend fun refreshToken(): Flow<ApiResult<RefreshTokenApiResponse>> {
        return networkBoundResource.downloadData {
            client.submitForm(
                url = URL_TOKEN_REFRESH,
                formParameters = parameters {
                    append("refreshToken", Prefs.refreshToken)
                }
            )
        }
    }
}


fun HttpResponse.isSuccessful() = this.status.value in 200..299

