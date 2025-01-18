package com.srizan.cruiseman.ui.screens.profile

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
class ProfileViewModel(
   @Provided private val authenticationService: AuthenticationService
) : ViewModel() {
    val uiState = mutableStateOf<ProfileUiState>(ProfileUiState.Idle)

    private val _events = Channel<ProfileUiEvent>()
    val events = _events.receiveAsFlow()

    fun logout() {
        viewModelScope.launch {
            authenticationService.logout().collect { result ->
                when (result) {
                    is ApiResult.ErrorState -> uiState.value =
                        ProfileUiState.Error(result.errorResponse.message)

                    is ApiResult.LoadingState -> uiState.value = ProfileUiState.Loading
                    is ApiResult.SuccessState -> {
                        Prefs.clearAll()
                        _events.send(ProfileUiEvent.NavigateLogin)
                    }
                }
            }
        }
    }
}

sealed interface ProfileUiState {
    data object Idle : ProfileUiState
    data object Loading : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}

sealed interface ProfileUiEvent {
    data object NavigateLogin : ProfileUiEvent
}

