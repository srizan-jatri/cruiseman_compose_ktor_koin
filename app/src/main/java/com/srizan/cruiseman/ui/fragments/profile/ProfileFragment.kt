package com.srizan.cruiseman.ui.fragments.profile

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
import com.srizan.cruiseman.data.sharedpref.Prefs
import com.srizan.cruiseman.ui.components.ErrorScreen
import com.srizan.cruiseman.ui.components.HandleEvent
import com.srizan.cruiseman.ui.components.LoadingScreen
import com.srizan.cruiseman.ui.screens.profile.ProfileScreen
import com.srizan.cruiseman.ui.screens.profile.ProfileUiEvent
import com.srizan.cruiseman.ui.screens.profile.ProfileUiState
import com.srizan.cruiseman.ui.screens.profile.ProfileViewModel
import com.srizan.cruiseman.ui.theme.CruiseManComposeKtorKoinTheme
import org.koin.compose.viewmodel.koinNavViewModel

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(inflater.context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            setContent {
                CruiseManComposeKtorKoinTheme {

                    val viewModel = koinNavViewModel<ProfileViewModel>()

                    HandleEvent(viewModel.events) { event ->
                        when (event) {
                            ProfileUiEvent.NavigateLogin -> {
                                findNavController().navigate(
                                    R.id.action_profileFragment_to_loginFragment
                                )
                            }
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
        }
    }
}