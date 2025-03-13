package ru.easycode.blogpostsintensive.login.presentation

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.easycode.blogpostsintensive.presentation.ScreenPreview
import ru.easycode.blogpostsintensive.ui.theme.BlogpostsintensiveTheme

/**
 * @author DaisyBell on 12.03.2025
 */
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<LoginViewModel>()

    val activity = LocalActivity.current as Activity
    val authResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.handleResult(AuthResultWrapper.Base(it)) {
                //todo: navcontroller. navigate to mainscreen
            }
        }

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    state.Update(viewModel)
    state.handle(authResult, activity)

}

@ScreenPreview
@Composable
fun LoginScreenPreview() {
    BlogpostsintensiveTheme {
        LoginScreen(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        )
    }
}