package com.srizan.cruiseman.ui.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.srizan.cruiseman.ui.components.ErrorScreen
import com.srizan.cruiseman.ui.components.HandleEvent
import com.srizan.cruiseman.ui.components.LoadingScreen
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
data object LoginScreenRoute

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.loginScreen(navigateProfile: () -> Unit) {
    composable<LoginScreenRoute> {

        val viewModel = koinNavViewModel<LoginViewModel>()

        HandleEvent(viewModel.events) { event ->
            when (event) {
                LoginUiEvent.NavigateProfile -> navigateProfile()
            }
        }

        when (viewModel.uiState.value) {
            is LoginUiState.Error -> ErrorScreen(
                message = (viewModel.uiState.value as LoginUiState.Error).message,
                onRetry = { viewModel.uiState.value = LoginUiState.Idle },
                modifier = Modifier.fillMaxSize()
            )

            is LoginUiState.Idle -> LoginScreen(
                onLoginClick = { phone, password ->
                    viewModel.login(phone, password)
                }
            )

            is LoginUiState.Loading -> LoadingScreen()
        }
    }
}

fun NavController.navigateToLoginScreen(navOptions: NavOptions? = null) {
    navigate(LoginScreenRoute, navOptions)
}