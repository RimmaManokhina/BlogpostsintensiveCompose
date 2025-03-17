package ru.easycode.blogpostsintensive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.easycode.blogpostsintensive.core.ConnectionViewModel
import ru.easycode.blogpostsintensive.core.NoInternet
import ru.easycode.blogpostsintensive.core.ScreenPreview
import ru.easycode.blogpostsintensive.navigation.RootNavigationGraph
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
        RootNavigationGraph(navController = rememberNavController())
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