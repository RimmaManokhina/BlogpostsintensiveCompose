package ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.profile.presentation

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
import ru.easycode.blogpostsintensive.presentation.ScreenPreview
import ru.easycode.blogpostsintensive.profile.my.MyProfileAndLogOut
import ru.easycode.blogpostsintensive.profile.my.SendPost
import ru.easycode.blogpostsintensive.ui.theme.BlogpostsintensiveTheme

/**
 * @author DaisyBell on 14.03.2025
 */
@Composable
fun MyProfileContent(
    userName: String,
    clickSendPost: (String) -> Unit,
    logout: () -> Unit,
    blogPostUiList: List<BlogPostUi>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        MyProfileAndLogOut(name = userName, logout)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(blogPostUiList.size) {
                blogPostUiList[it].Show()
            }
        }
        SendPost(handlePost = clickSendPost)
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
            clickSendPost = {},
            logout = {},
            blogPostUiList = listOf(
                BlogPostUi.Base(
                    message = "message",
                )
            )
        )
    }
}