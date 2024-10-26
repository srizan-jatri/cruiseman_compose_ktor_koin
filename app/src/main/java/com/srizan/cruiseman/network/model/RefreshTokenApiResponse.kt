package com.srizan.cruiseman.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenApiResponse(
    val `data`: TokenData?,
    val message: String?,
    val statusCode: Int?,
    //val error: String?
)

