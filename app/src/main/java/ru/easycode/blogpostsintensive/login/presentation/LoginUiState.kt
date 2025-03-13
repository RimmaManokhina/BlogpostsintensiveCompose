package ru.easycode.blogpostsintensive.login.presentation

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.core.ManageResource

interface LoginUiState {

    fun handle(launcher: ActivityResultLauncher<Intent>, activity: Activity) = Unit

    @Composable
    fun Update(viewModel: LoginViewModel) = Unit

    object Initial : LoginUiState {
        @Composable
        override fun Update(viewModel: LoginViewModel) {
            LoginButton(Modifier) {
                viewModel.login()
            }
        }
    }

    data class Error(private val message: String) : LoginUiState {
        @Composable
        override fun Update(viewModel: LoginViewModel) {
            Column {
                Text(text = message)
                LoginButton(Modifier) {
                    viewModel.login()
                }
            }
        }
    }

    data class Auth(private val manageResource: ManageResource) : LoginUiState {

        private val options by lazy {
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(manageResource.string(R.string.your_web_client_id))
                .requestEmail()
                .requestProfile()
                .requestScopes(Scope(Scopes.DRIVE_FILE))
                .build()
        }

        override fun handle(launcher: ActivityResultLauncher<Intent>, activity: Activity) {
            launcher.launch(GoogleSignIn.getClient(activity, options).signInIntent)
        }

        @Composable
        override fun Update(viewModel: LoginViewModel) {
            CircularProgressIndicator()
        }
    }
}