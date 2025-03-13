package ru.easycode.blogpostsintensive.login.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.easycode.blogpostsintensive.core.BaseViewModel
import ru.easycode.blogpostsintensive.core.ManageResource
import ru.easycode.blogpostsintensive.core.RunAsync
import ru.easycode.blogpostsintensive.login.data.LoginRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val manageResource: ManageResource,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    private val _viewState = MutableStateFlow<LoginUiState>(LoginUiState.Auth(manageResource))
    val viewState: StateFlow<LoginUiState> = _viewState.asStateFlow()

    init {
        if (repository.userNotLoggedIn())
            _viewState.value = LoginUiState.Initial
        else
            login()
    }

    fun handleResult(authResult: AuthResultWrapper, navigate: () -> Unit) = runAsync({
        repository.handleResult(authResult)
    }) {
        it.map(_viewState, navigate)
    }

    fun login() {
        _viewState.value = LoginUiState.Auth(manageResource)
    }
}