package com.srizan.cruiseman.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LogoutApiResponse(
    val message: String?,
    val statusCode: Int?
)