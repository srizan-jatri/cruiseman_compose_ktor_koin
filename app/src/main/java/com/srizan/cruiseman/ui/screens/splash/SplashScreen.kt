package com.srizan.cruiseman.ui.screens.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.srizan.cruiseman.ui.components.ErrorScreen
import com.srizan.cruiseman.ui.components.LoadingScreen

@Composable
fun SplashScreen(
    uiState: SplashUiState,
    onRetry: () -> Unit
) {
    when (uiState) {
        is SplashUiState.Error -> ErrorScreen(
            message = uiState.message,
            onRetry = onRetry,
            Modifier.fillMaxSize()
        )

        SplashUiState.Loading -> LoadingScreen()
    }
}



