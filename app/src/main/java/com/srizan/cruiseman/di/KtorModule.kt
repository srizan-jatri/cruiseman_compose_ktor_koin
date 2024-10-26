package com.srizan.cruiseman.di

import com.srizan.cruiseman.data.sharedpref.Prefs
import com.srizan.cruiseman.network.TokenRefresher
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import kotlinx.serialization.json.Json
import org.koin.dsl.module

const val BASE_URL_AUTH = "https://dev.api.auth.waterways.jatri.co:8160"

val HttpModule = module {
    single<HttpClient> {
        val client = HttpClient(CIO) {

            defaultRequest {
                url(BASE_URL_AUTH)
                headers.appendIfNameAbsent("Panel-Name", "cruiseman")
            }

            /** Model Serialization */
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }

            /** Http Logger */
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }

            /** Bearer authentication */
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(Prefs.accessToken, Prefs.refreshToken)
                    }

                    refreshTokens {
                        get<TokenRefresher>().refresh()
                    }
                }
            }
        }

        /** Interceptor for setting panel name */
        client.plugin(HttpSend).intercept { request ->
            //request.headers.set("Panel-Name", "cruiseman")
            execute(request)
        }
        client
    }
}

