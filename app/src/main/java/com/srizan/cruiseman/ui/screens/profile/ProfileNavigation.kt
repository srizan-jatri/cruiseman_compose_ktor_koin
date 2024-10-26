package com.srizan.cruiseman.ui.screens.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.srizan.cruiseman.data.sharedpref.Prefs
import com.srizan.cruiseman.ui.components.ErrorScreen
import com.srizan.cruiseman.ui.components.HandleEvent
import com.srizan.cruiseman.ui.components.LoadingScreen
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@Serializable
data object ProfileScreenRoute

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.profileScreen(navigateLogin: () -> Unit) {
    composable<ProfileScreenRoute> {
        val viewModel = koinNavViewModel<ProfileViewModel>()

        HandleEvent(viewModel.events) { event ->
            when (event) {
                ProfileUiEvent.NavigateLogin -> navigateLogin()
            }
        }


        when (viewModel.uiState.value) {
            is ProfileUiState.Error -> ErrorScreen(
                message = (viewModel.uiState.value as ProfileUiState.Error).message,
                onRetry = { viewModel.logout() },
                modifier = Modifier.fillMaxSize()
            )

            is ProfileUiState.Idle -> ProfileScreen(
                name = Prefs.name,
                phone = Prefs.phone,
                address = Prefs.address,
                onLogoutClick = { viewModel.logout() },
            )

            is ProfileUiState.Loading -> LoadingScreen()
        }
    }
}

fun NavController.navigateToProfileScreen(navOptions: NavOptions? = null) {
    navigate(ProfileScreenRoute, navOptions)
}