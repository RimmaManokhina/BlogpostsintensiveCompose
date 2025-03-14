package ru.easycode.blogpostsintensive.search.data

import ru.easycode.blogpostsintensive.search.presentation.SearchUserModel

interface SearchRepository {

    suspend fun findUsers(userEmail: String): List<SearchUserModel>
}