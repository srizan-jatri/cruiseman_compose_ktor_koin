package com.srizan.cruiseman.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestBody(
    val phone: String,
    val password: String,
    val deviceToken: String
)