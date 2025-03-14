package ru.easycode.blogpostsintensive.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

interface DateConverter {

    fun getUTCDate(): Long

    fun convertToLocal(date: Long): String

    class Base @Inject constructor(
        pattern: String,
        locale: Locale
    ) : DateConverter {

        private val localFormatter = SimpleDateFormat(pattern, locale)

        override fun getUTCDate(): Long = Date().time

        override fun convertToLocal(date: Long): String {
            localFormatter.timeZone = TimeZone.getDefault()
            return localFormatter.format(Date(date))
        }
    }
}