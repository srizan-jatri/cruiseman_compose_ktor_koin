package com.srizan.cruiseman.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val statusCode: Int = 0,
    val message: String = "",
    val error: String = ""
)
