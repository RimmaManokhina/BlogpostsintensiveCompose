package ru.easycode.blogpostsintensive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import ru.easycode.blogpostsintensive.core.ConnectionViewModel
import ru.easycode.blogpostsintensive.core.NoInternet
import ru.easycode.blogpostsintensive.login.presentation.LoginScreen
import ru.easycode.blogpostsintensive.presentation.ScreenPreview
import ru.easycode.blogpostsintensive.ui.theme.BlogpostsintensiveTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlogpostsintensiveTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        HandleInternetConnection()
        LoginScreen(Modifier.background(MaterialTheme.colorScheme.background))
    }
}

@Composable
internal fun HandleInternetConnection() {
    val connectionViewModel: ConnectionViewModel = hiltViewModel()

    val isConnected by connectionViewModel.connectedStateFlow.collectAsStateWithLifecycle()

    if (!isConnected) {
        NoInternet()
    }
}

@ScreenPreview
@Composable
fun MainScreenPreview() {
    BlogpostsintensiveTheme {
        MainScreen()
    }
}