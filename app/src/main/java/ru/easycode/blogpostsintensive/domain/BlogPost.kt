package ru.easycode.blogpostsintensive.domain

interface BlogPost {

    interface Mapper<T : Any> {

        fun toBase(
            id: String,
            ownerId: String,
            text: String,
        ): T

    }

    fun <T : Any> map(mapper: Mapper<T>): T

    fun message(): String = ""

    data class Base(
        private val id: String,
        private val ownerId: String,
        private val text: String,
    ) : BlogPost {

        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.toBase(id, ownerId, text)
    }
}