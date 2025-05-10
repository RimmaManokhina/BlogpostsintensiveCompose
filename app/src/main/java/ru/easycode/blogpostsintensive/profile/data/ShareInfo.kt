package ru.easycode.blogpostsintensive.profile.data

data class ShareInfo(
    private val userName: String,
    private val message: String
) {
    fun text() = "$message \n \n $userName"
}
