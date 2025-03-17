package ru.easycode.blogpostsintensive.profile.presentation

import androidx.compose.runtime.Composable
import ru.easycode.blogpostsintensive.post.PostContent
import ru.easycode.blogpostsintensive.post.domain.PostData

interface BlogPostUi : PostData {

    @Composable
    fun Show() = Unit

    fun userName(): String = ""

    data class Base(
        private val message: String,
    ) : BlogPostUi {

        @Composable
        override fun Show() {
            PostContent(text = message)
        }

        override fun message(): String = message

    }
}