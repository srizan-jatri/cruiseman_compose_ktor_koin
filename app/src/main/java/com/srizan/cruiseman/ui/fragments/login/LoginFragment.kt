package com.srizan.cruiseman.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.srizan.cruiseman.R
import com.srizan.cruiseman.ui.components.ErrorScreen
import com.srizan.cruiseman.ui.components.HandleEvent
import com.srizan.cruiseman.ui.components.LoadingScreen
import com.srizan.cruiseman.ui.screens.login.LoginScreen
import com.srizan.cruiseman.ui.screens.login.LoginUiEvent
import com.srizan.cruiseman.ui.screens.login.LoginUiState
import com.srizan.cruiseman.ui.screens.login.LoginViewModel
import com.srizan.cruiseman.ui.theme.CruiseManComposeKtorKoinTheme
import org.koin.compose.viewmodel.koinNavViewModel

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(inflater.context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            setContent {
                CruiseManComposeKtorKoinTheme {

                    val viewModel = koinNavViewModel<LoginViewModel>()

                    HandleEvent(viewModel.events) { event ->
                        when (event) {
                            LoginUiEvent.NavigateProfile -> {
                                findNavController().navigate(
                                    R.id.action_loginFragment_to_profileFragment
                                )
                            }
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
        }
    }
}