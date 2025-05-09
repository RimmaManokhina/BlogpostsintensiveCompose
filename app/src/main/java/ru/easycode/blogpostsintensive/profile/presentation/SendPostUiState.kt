package ru.easycode.blogpostsintensive.profile.presentation

import androidx.compose.runtime.Composable
import ru.easycode.blogpostsintensive.profile.domain.CreatePost
import ru.easycode.blogpostsintensive.profile.my.EditPost
import ru.easycode.blogpostsintensive.profile.my.SendPost

interface SendPostUiState {

    @Composable
    fun Show()

    fun sendPost(message: String)

    data class Edit(
        private val postId: String,
        private val message: String,
        private val sendAndHide: SendAndHideEdit
    ) : SendPostUiState {
        @Composable
        override fun Show() {
            EditPost(
                message = message,
                hideEdit = { sendAndHide.hideEdit() }
            ) { text ->
                sendAndHide.handleSendEdited(postId = postId, text = text)
            }
        }

        override fun sendPost(message: String) = sendAndHide.handleSendEdited(postId, message)
    }

    data class Create(
        private val create: CreatePost
    ) : SendPostUiState {
        @Composable
        override fun Show() {
            SendPost {
                create.createPost(it)
            }
        }

        override fun sendPost(message: String) = create.createPost(message)
    }

    //only for Preview
    object Empty : SendPostUiState {
        @Composable
        override fun Show() = Unit

        override fun sendPost(message: String) = Unit
    }
}