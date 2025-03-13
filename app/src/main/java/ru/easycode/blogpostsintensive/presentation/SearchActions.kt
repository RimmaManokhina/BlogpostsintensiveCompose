package ru.easycode.blogpostsintensive.presentation

interface SearchActions {

    fun showAllPosts()

    fun findPostsByQuery(filter: PostsFilter)

    fun filterPosts()

    fun resetFilters()
}