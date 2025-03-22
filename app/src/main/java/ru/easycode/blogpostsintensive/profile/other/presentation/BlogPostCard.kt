package ru.easycode.blogpostsintensive.profile.other.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.easycode.blogpostsintensive.post.PostContent

interface BlogPostCard {

    @Composable
    fun Show()

    data class Base(
        val id: String,
        val message: String,
    ) : BlogPostCard {

        @Composable
        override fun Show() {
            PostContent(
                text = message
            )
        }
    }
}

@Preview(showBackground = true, name = "Blog Post Card Preview")
@Composable
fun PreviewBlogPost() {
    BlogPostCard.Base(
        id = "1",
        message = "PostText PostText PostText PostText PostText " +
                "PostText PostText PostText PostText PostText " +
                "PostText PostText PostText PostText PostText " +
                "PostText PostText PostText PostText PostText "
    ).Show()
}