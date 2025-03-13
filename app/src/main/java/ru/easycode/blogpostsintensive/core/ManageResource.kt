package ru.easycode.blogpostsintensive.core

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ManageResource {

    fun string(id: Int): String

    class Base @Inject constructor(@ApplicationContext private val context: Context) :
        ManageResource {

        override fun string(id: Int) = context.getString(id)
    }
}