package ru.easycode.blogpostsintensive.common

data class PostCloud(
    val ownerId: String = "",
    val post: String = "",
    val originalPostId: String? = null,
    val createdAt: Long = 0L,
    val editedAt: Long = 0L,
    val imageLink: String = ""
)
