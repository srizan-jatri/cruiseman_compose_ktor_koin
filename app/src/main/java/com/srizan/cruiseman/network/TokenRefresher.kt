package com.srizan.cruiseman.network

import com.srizan.cruiseman.data.URL_TOKEN_REFRESH
import com.srizan.cruiseman.data.isSuccessful
import com.srizan.cruiseman.data.sharedpref.Prefs
import com.srizan.cruiseman.network.model.RefreshTokenApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.request.forms.submitForm
import io.ktor.http.parameters
import org.koin.core.annotation.Single

@Single
class TokenRefresher(val client: HttpClient) {
    suspend fun refresh(): BearerTokens {

        val response = client.submitForm(
            url = URL_TOKEN_REFRESH,
            formParameters = parameters {
                append("refreshToken", Prefs.refreshToken)
            }
        )

        if (response.isSuccessful()) {
            val body = response.body<RefreshTokenApiResponse>()
            Prefs.accessToken = body.data?.accessToken ?: ""
            Prefs.refreshToken = body.data?.refreshToken ?: ""
        }

        return BearerTokens(Prefs.accessToken, Prefs.refreshToken)
    }
}