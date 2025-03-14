package ru.easycode.blogpostsintensive.search.presentation

interface SearchUserModel {
    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun map(id: String, name: String, email: String): T
    }

    data class Base(
        private val id: String,
        private val name: String,
        private val email: String
    ) : SearchUserModel {
        override fun <T : Any> map(mapper: Mapper<T>) = mapper.map(id, name, email)
    }
}