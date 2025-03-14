package ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ru.easycode.blogpostsintensive.navigation.Graph

/**
 * @author DaisyBell on 14.03.2025
 */
@Composable
fun MyProfileScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<MyProfileViewModel>()

    val state by viewModel.viewState.collectAsStateWithLifecycle()

    MyProfileContent(
        userName = viewModel.userInfo(),
        clickSendPost = { viewModel.createPost(it) },
        logout = {
            viewModel.logout()
            navController.navigate(Graph.LOGIN) {
                popUpTo(Graph.HOME) {
                    inclusive = true
                }
            }
        },
        blogPostUiList = state
    )
}