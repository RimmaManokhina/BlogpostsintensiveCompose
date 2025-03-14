package ru.easycode.blogpostsintensive.domain

interface BlogPost {

    interface Mapper<T : Any> {

        fun toBase(
            text: String,
        ): T

    }

    fun <T : Any> map(mapper: Mapper<T>): T

    fun message(): String = ""

    data class Base(
        private val text: String,
    ) : BlogPost {

        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.toBase(text)

    }
}