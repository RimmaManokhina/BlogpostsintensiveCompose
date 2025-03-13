package ru.easycode.blogpostsintensive.core

import android.content.Context
import java.util.Locale

interface ProvideLocal {

    fun provide(): Locale

    class Base(private val context: Context) : ProvideLocal {

        override fun provide() = context.resources.configuration.locales[0]
    }
}