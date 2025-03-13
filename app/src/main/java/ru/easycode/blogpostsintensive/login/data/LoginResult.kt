package ru.easycode.blogpostsintensive.login.data

import kotlinx.coroutines.flow.MutableStateFlow
import ru.easycode.blogpostsintensive.login.presentation.LoginUiState

interface LoginResult {

    fun map(viewState: MutableStateFlow<LoginUiState>, navigate: () -> Unit)

    object Success : LoginResult {

        override fun map(
            viewState: MutableStateFlow<LoginUiState>,
            navigate: () -> Unit
        ) = navigate.invoke()
    }

    data class Failed(private val message: String) : LoginResult {

        override fun map(
            viewState: MutableStateFlow<LoginUiState>,
            navigate: () -> Unit
        ) {
            viewState.value = LoginUiState.Error(message)
        }
    }
}