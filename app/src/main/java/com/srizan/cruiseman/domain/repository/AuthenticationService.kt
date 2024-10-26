package com.srizan.cruiseman.domain.repository

import com.srizan.cruiseman.domain.entity.ApiResult
import com.srizan.cruiseman.domain.entity.LoginApiEntity
import com.srizan.cruiseman.domain.entity.ProfileApiEntity
import com.srizan.cruiseman.network.model.LogoutApiResponse
import com.srizan.cruiseman.network.model.RefreshTokenApiResponse
import kotlinx.coroutines.flow.Flow

interface AuthenticationService {
    suspend fun login(phone: String, password: String): Flow<ApiResult<LoginApiEntity>>
    suspend fun logout(): Flow<ApiResult<LogoutApiResponse>>
    suspend fun getProfile(): Flow<ApiResult<ProfileApiEntity>>
    suspend fun refreshToken(): Flow<ApiResult<RefreshTokenApiResponse>>
}