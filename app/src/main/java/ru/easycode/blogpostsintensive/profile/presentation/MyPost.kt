package ru.easycode.blogpostsintensive.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.profile.presentation.ShareButton

interface MyPost {

    @Composable
    fun Show(actions: PostActions)

    class Base(
        private val post: BlogPostUi,
        private val postId: String,
    ) : MyPost {

        @Composable
        override fun Show(actions: PostActions) {

            Card(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.Companion.padding(8.dp)) {
                    post.Show()
                    Row(
                        modifier = Modifier.Companion.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        EditButton { actions.editPost(postId, post.message()) }

                        DeleteButton { actions.deletePost(postId) }

                        ShareButton(shareInfo = actions.shareInfo(post))
                    }
                }
            }
        }
    }
}