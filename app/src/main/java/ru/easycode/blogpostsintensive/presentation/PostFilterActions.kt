package ru.easycode.blogpostsintensive.presentation

interface PostFilterActions {

    fun applyFilter(filter: PostsFilter)

    fun clearFilter()
}