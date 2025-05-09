package ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import javax.inject.Inject

interface DateConverter {
    fun getUTCDate(): Long

    fun convertToLocal(date: Long): String

    class Base @Inject constructor(
        private val locale: ProvideLocal,
    ) : DateConverter {
        private val pattern: String = "HH:mm dd MMMM yyyy"

        private val localFormatter = SimpleDateFormat(pattern, locale.provide())

        override fun getUTCDate(): Long = Date().time

        override fun convertToLocal(date: Long): String {
            localFormatter.timeZone = TimeZone.getDefault()
            return localFormatter.format(Date(date))
        }
    }
}