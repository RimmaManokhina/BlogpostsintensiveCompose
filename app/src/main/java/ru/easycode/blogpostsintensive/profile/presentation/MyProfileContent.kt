package ru.easycode.blogpostsintensive.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.easycode.blogpostsintensive.core.ScreenPreview
import ru.easycode.blogpostsintensive.profile.my.MyProfileAndLogOut
import ru.easycode.blogpostsintensive.ui.theme.BlogpostsintensiveTheme

/**
 * @author DaisyBell on 14.03.2025
 */
@Composable
fun MyProfileContent(
    userName: String,
    logout: () -> Unit,
    myPosts: List<MyPost>,
    sendState: SendPostUiState,
    actions: PostActions
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        MyProfileAndLogOut(name = userName, logout)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(myPosts.count()) { index ->
                myPosts[index].Show(actions)
            }
        }

        sendState.Show()

        //todo: BottomBar закрывает нижнее пространство для этого сделала спейсер.
        // Костыль, возможно нужно будет исправить.
        Spacer(modifier = Modifier.height(100.dp))
    }

}

@ScreenPreview
@Composable
fun MyProfileScreenPreview() {
    BlogpostsintensiveTheme {
        MyProfileContent(
            userName = "userName",
            logout = {},
            myPosts = listOf(
                MyPost.Base(
                    post = BlogPostUi.Base(
                        message = "message"
                    ),
                    postId = "14"
                )
            ),
            sendState = SendPostUiState.Empty,
            actions = PostActions.Empty
        )
    }
}