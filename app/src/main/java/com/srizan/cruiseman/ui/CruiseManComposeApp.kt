package com.srizan.cruiseman.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.srizan.cruiseman.navigation.AppNavHost
import com.srizan.cruiseman.ui.screens.splash.SplashScreenRoute
import com.srizan.cruiseman.ui.theme.CruiseManComposeKtorKoinTheme

@Composable
fun CruiseManComposeApp() {
    CruiseManComposeKtorKoinTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppNavHost(
                navController = rememberNavController(),
                startDestination = SplashScreenRoute,
                Modifier.padding(innerPadding)
            )
        }
    }
}