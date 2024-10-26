package com.srizan.cruiseman.ui.screens.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.srizan.cruiseman.ui.components.HandleEvent
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
data object SplashScreenRoute


@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.splashScreen(
    navigateLogin: () -> Unit,
    navigateProfile: () -> Unit,
) {
    composable<SplashScreenRoute> {

        val viewModel = koinNavViewModel<SplashViewModel>()
        HandleEvent(viewModel.events) { event ->
            when (event) {
                SplashUiEvent.NavigateToProfileScreen -> navigateProfile()
                SplashUiEvent.NavigateToLoginScreen -> navigateLogin()
            }
        }

        SplashScreen(uiState = viewModel.state.value, onRetry = { viewModel.getProfile() })
    }
}