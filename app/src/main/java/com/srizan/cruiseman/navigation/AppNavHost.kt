package com.srizan.cruiseman.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.srizan.cruiseman.ui.screens.login.LoginScreenRoute
import com.srizan.cruiseman.ui.screens.login.loginScreen
import com.srizan.cruiseman.ui.screens.login.navigateToLoginScreen
import com.srizan.cruiseman.ui.screens.profile.ProfileScreenRoute
import com.srizan.cruiseman.ui.screens.profile.navigateToProfileScreen
import com.srizan.cruiseman.ui.screens.profile.profileScreen
import com.srizan.cruiseman.ui.screens.splash.splashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        splashScreen(
            navigateLogin = {
                navController.navigateToLoginScreen(navOptions {
                    popUpTo(navController.graph.findStartDestination().id) {
                        this.inclusive = true
                    }
                })
            },
            navigateProfile = {
                navController.navigateToProfileScreen(
                    navOptions {
                        popUpTo(navController.graph.findStartDestination().id) {
                            this.inclusive = true
                        }
                    }
                )
            }
        )

        loginScreen(navigateProfile = {
            navController.navigateToProfileScreen(navOptions {
                popUpTo(LoginScreenRoute) {
                    this.inclusive = true
                }
            })

        })
        profileScreen(navigateLogin = {
            navController.navigateToLoginScreen(navOptions {
                popUpTo(ProfileScreenRoute) {
                    this.inclusive = true
                }
            })
        })
    }
}