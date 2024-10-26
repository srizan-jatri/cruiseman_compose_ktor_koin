package com.srizan.cruiseman.domain.entity


data class LoginApiEntity(
    val token: TokenData,
    val message: String,
    val statusCode: Int,
)


data class TokenData(
    val accessToken: String,
    val refreshToken: String
)