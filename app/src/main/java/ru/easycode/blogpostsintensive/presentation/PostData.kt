package ru.easycode.blogpostsintensive.presentation

interface PostData {

    fun message(): String

    fun lastChanged(): Long
}