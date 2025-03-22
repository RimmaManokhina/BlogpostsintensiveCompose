package ru.easycode.blogpostsintensive.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.easycode.blogpostsintensive.core.UserNameUi

interface SearchUserCard {

    @Composable
    fun Show(onClick: (String) -> Unit)

    fun userName(): String

    data class Base(
        private val id: String,
        private val text: String
    ) : SearchUserCard {

        @Composable
        override fun Show(onClick: (String) -> Unit) {
            UserNameUi(
                onClick = onClick,
                userId = id,
                userName = text
            )
        }

        override fun userName(): String = text
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSearchUser() {
    SearchUserCard.Base(
        id = "1",
        text = "Sample User"
    ).Show(onClick = {})
}