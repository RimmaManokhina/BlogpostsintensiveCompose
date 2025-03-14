package ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.profile.domain

import ru.easycode.blogpostsintensive.domain.BlogPost

interface MyProfileRepository {

    fun userName(): Pair<String, String>

    fun logout()

    suspend fun init(): List<BlogPost>

    suspend fun createPost(text: String): List<BlogPost>

}