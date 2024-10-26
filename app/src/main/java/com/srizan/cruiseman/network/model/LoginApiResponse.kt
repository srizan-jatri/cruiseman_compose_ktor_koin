package com.srizan.cruiseman.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginApiResponse(
    val `data`: TokenData?,
    val message: String?,
    val statusCode: Int?,
)

@Serializable
data class TokenData(
    val accessToken: String?,
    val refreshToken: String?
)