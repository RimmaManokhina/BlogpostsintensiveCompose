package ru.easycode.blogpostsintensive.profile.presentation

import androidx.compose.runtime.Composable
import ru.easycode.blogpostsintensive.post.PostContent
import ru.easycode.blogpostsintensive.post.domain.PostData

interface BlogPostUi : PostData {

    @Composable
    fun Show()

    fun ownerId(): String

    fun id(): String

    fun userName(): String = ""

    data class Base(
        private val id: String,
        private val ownerId: String,
        private val message: String,
    ) : BlogPostUi {

        override fun id(): String = id

        override fun ownerId(): String = ownerId

        @Composable
        override fun Show() {
            PostContent(text = message)
        }

        override fun message(): String = message

    }
}