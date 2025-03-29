package ru.easycode.blogpostsintensive.profile.other.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.easycode.blogpostsintensive.core.UserNameUi

@Composable
fun OtherProfileScreen(
    userId: String,
    userName: String,
    onBackClick: () -> Unit
) {
    val viewModel: OtherProfileViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.init(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(8.dp)
            .padding(vertical = 36.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .clickable { onBackClick() }
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            UserNameUi(
                onClick = { },
                userName = userName,
                userId = userId
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (viewState.isSubscribed != null) {
            SubscribeButton(
                isSubscribedInitial = viewState.isSubscribed ?: false,
                onSubscriptionChange = {
                    viewModel.updateSubscription(userId)
                }
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        if (viewState.posts.isNotEmpty()) {
            LazyColumn {
                items(viewState.posts) { post ->
                    post.Show()
                }
            }
        } else {
            Text(text = "Постов пока нет", modifier = Modifier.padding(16.dp))
        }
    }
}
