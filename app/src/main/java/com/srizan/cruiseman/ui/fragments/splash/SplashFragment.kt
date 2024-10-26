package com.srizan.cruiseman.ui.fragments.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.srizan.cruiseman.R
import com.srizan.cruiseman.ui.components.HandleEvent
import com.srizan.cruiseman.ui.screens.splash.SplashScreen
import com.srizan.cruiseman.ui.screens.splash.SplashUiEvent
import com.srizan.cruiseman.ui.screens.splash.SplashViewModel
import com.srizan.cruiseman.ui.theme.CruiseManComposeKtorKoinTheme
import org.koin.compose.viewmodel.koinNavViewModel

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(inflater.context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            setContent {
                CruiseManComposeKtorKoinTheme {

                    val viewModel = koinNavViewModel<SplashViewModel>()
                    HandleEvent(viewModel.events) { event ->
                        when (event) {
                            SplashUiEvent.NavigateToProfileScreen -> {
                                findNavController().navigate(
                                    R.id.action_splashFragment_to_profileFragment
                                )
                            }

                            SplashUiEvent.NavigateToLoginScreen -> {
                                findNavController().navigate(
                                    R.id.action_splashFragment_to_loginFragment
                                )
                            }
                        }
                    }

                    SplashScreen(
                        uiState = viewModel.state.value,
                        onRetry = { viewModel.getProfile() })
                }
            }
        }
    }
}