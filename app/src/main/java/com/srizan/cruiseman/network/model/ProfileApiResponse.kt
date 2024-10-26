package com.srizan.cruiseman.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileApiResponse(
    val `data`: UserInfoResponse?,
    val message: String?,
    val statusCode: Int?
)

@Serializable
data class UserInfoResponse(
    val user: UserResponse?
)

@Serializable
data class UserResponse(
    val address: String?,
    val name: String?,
    val phone: String?,
)