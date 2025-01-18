package com.srizan.cruiseman.ui.screens.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srizan.cruiseman.data.sharedpref.Prefs
import com.srizan.cruiseman.domain.entity.ApiResult
import com.srizan.cruiseman.domain.repository.AuthenticationService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Provided

@KoinViewModel
class SplashViewModel(
    @Provided private val authenticationService: AuthenticationService
) : ViewModel() {

    val state = mutableStateOf<SplashUiState>(SplashUiState.Loading)

    private val _events = Channel<SplashUiEvent>()
    val events = _events.receiveAsFlow()

    init {


        viewModelScope.launch {
            if (Prefs.isUserLoggedIn.not()) {
                delay(1000)
                _events.send(SplashUiEvent.NavigateToLoginScreen)
            } else getProfile()
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            authenticationService.getProfile().collect { result ->
                when (result) {
                    is ApiResult.ErrorState -> {
                        if (result.errorResponse.statusCode == 401) _events.send(SplashUiEvent.NavigateToLoginScreen)
                        else state.value =
                            SplashUiState.Error(message = result.errorResponse.message)
                    }

                    is ApiResult.LoadingState -> {
                        state.value = SplashUiState.Loading
                    }

                    is ApiResult.SuccessState -> {
                        with(Prefs) {
                            name = result.data.data.user.name
                            phone = result.data.data.user.phone
                            address = result.data.data.user.address
                        }
                        _events.send(SplashUiEvent.NavigateToProfileScreen)
                    }
                }
            }
        }
    }
}

sealed interface SplashUiState {
    data object Loading : SplashUiState
    data class Error(val message: String) : SplashUiState
}

sealed interface SplashUiEvent {
    data object NavigateToProfileScreen : SplashUiEvent
    data object NavigateToLoginScreen : SplashUiEvent
}