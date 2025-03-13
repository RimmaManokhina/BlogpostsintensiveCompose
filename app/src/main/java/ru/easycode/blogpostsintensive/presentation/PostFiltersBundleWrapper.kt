package ru.easycode.blogpostsintensive.presentation

import android.os.Bundle
import ru.easycode.blogpostsintensive.core.getSerializableWithDiffVersions

interface PostFiltersBundleWrapper {

    fun save(queryFilter: PostsFilter, postedDateFilter: PostsFilter)

    fun restore(): Pair<PostsFilter, PostsFilter>

    class Base(private val bundle: Bundle) : PostFiltersBundleWrapper {

        private val queryFilterKey = "QUERY_FILTER_KEY"
        private val postedDateFilterKey = "DATE_FILTER_KEY"

        override fun save(queryFilter: PostsFilter, postedDateFilter: PostsFilter) {
            bundle.putSerializable(queryFilterKey, queryFilter)
            bundle.putSerializable(postedDateFilterKey, postedDateFilter)
        }

        override fun restore(): Pair<PostsFilter, PostsFilter> = Pair(
            bundle.getSerializableWithDiffVersions(queryFilterKey),
            bundle.getSerializableWithDiffVersions(postedDateFilterKey)
        )
    }
}