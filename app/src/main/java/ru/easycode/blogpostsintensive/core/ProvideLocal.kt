package ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.core

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject

interface ProvideLocal {

    fun provide(): Locale

    class Base @Inject constructor (@ApplicationContext private val context: Context) :
        ProvideLocal {

        override fun provide() = context.resources.configuration.locales[0]
    }
}