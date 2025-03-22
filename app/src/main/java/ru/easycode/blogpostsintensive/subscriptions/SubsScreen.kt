package ru.easycode.blogpostsintensive.subscriptions

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ru.easycode.blogpostsintensive.core.UserNameUi
import ru.easycode.blogpostsintensive.navigation.navigateToOtherProfile

@Composable
fun SubsScreen(navController: NavController) {

    val viewModel: SubsViewModel = hiltViewModel()

    val subsList by viewModel.subscribedUsers.collectAsStateWithLifecycle()

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(subsList.size) {
            UserNameUi(
                onClick = { userId ->
                    navController.navigateToOtherProfile(
                        userId = userId,
                        userName = subsList[it].name
                    )
                },
                userName = subsList[it].name,
                userId = subsList[it].id
            )
        }
    }
}