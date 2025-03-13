package ru.easycode.blogpostsintensive.presentation

import java.io.Serializable

interface Now : Serializable {

    fun currentTimeMillis(): Long

    class Base : Now {

        override fun currentTimeMillis() = System.currentTimeMillis()
    }
}