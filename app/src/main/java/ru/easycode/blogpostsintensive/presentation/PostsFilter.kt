package ru.easycode.blogpostsintensive.presentation

import java.io.Serializable

interface PostsFilter : (PostData) -> Boolean, Serializable {

    class Default : PostsFilter {

        override fun invoke(p1: PostData) = true
    }

    data class Base(private val query: String) : PostsFilter {

        override fun invoke(p1: PostData): Boolean {
            return p1.message().contains(query, ignoreCase = true)
        }
    }

    abstract class DateFilter(private val hours: Int, private val now: Now) : PostsFilter {

        override fun invoke(p1: PostData): Boolean =
            now.currentTimeMillis() - p1.lastChanged() < hours * 3600 * 1000L
    }

    data class LastHourFilter(private val now: Now) : DateFilter(1, now)

    data class LastDayFilter(private val now: Now) : DateFilter(24, now)

    data class LastWeekFilter(private val now: Now) : DateFilter(7 * 24, now)

    data class LastMonthFilter(private val now: Now) : DateFilter(30 * 24, now)

    data class LastYearFilter(private val now: Now) : DateFilter(365 * 24, now)
}