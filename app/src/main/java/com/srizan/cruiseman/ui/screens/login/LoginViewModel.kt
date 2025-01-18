package com.srizan.cruiseman.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srizan.cruiseman.data.sharedpref.Prefs
import com.srizan.cruiseman.domain.entity.ApiResult
import com.srizan.cruiseman.domain.repository.AuthenticationService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Provided

@KoinViewModel
class LoginViewModel(@Provided private val authenticationService: AuthenticationService) : ViewModel() {

    val uiState = mutableStateOf<LoginUiState>(LoginUiState.Idle)

    private val _events = Channel<LoginUiEvent>()
    val events = _events.receiveAsFlow()

    fun login(phone: String, password: String) {
        viewModelScope.launch {
            authenticationService.login(phone, password).collect { result ->
                when (result) {
                    is ApiResult.ErrorState -> {
                        uiState.value = LoginUiState.Error(result.errorResponse.message)
                    }

                    is ApiResult.LoadingState -> {
                        uiState.value = LoginUiState.Loading
                    }

                    is ApiResult.SuccessState -> {
                        with(Prefs) {
                            isUserLoggedIn = true
                            accessToken = result.data.token.accessToken
                            refreshToken = result.data.token.refreshToken
                        }
                        getProfile()
                    }
                }
            }
        }
    }


    private suspend fun getProfile() {
        authenticationService.getProfile().collect { result ->
            when (result) {
                is ApiResult.ErrorState -> {
                    uiState.value = LoginUiState.Error(result.errorResponse.message)
                }

                is ApiResult.LoadingState -> {
                    uiState.value = LoginUiState.Loading
                }

                is ApiResult.SuccessState -> {
                    with(Prefs) {
                        name = result.data.data.user.name
                        phone = result.data.data.user.phone
                        address = result.data.data.user.address
                    }
                    _events.send(LoginUiEvent.NavigateProfile)
                }
            }
        }
    }
}

sealed interface LoginUiState {
    data object Idle : LoginUiState
    data object Loading : LoginUiState
    data class Error(val message: String) : LoginUiState
}

sealed interface LoginUiEvent {
    data object NavigateProfile : LoginUiEvent
}