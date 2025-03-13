package ru.easycode.blogpostsintensive.core

interface UserMapper: Mapper<Pair<String, String>, String> {

    class Base: UserMapper {
        override fun map(source: Pair<String, String>): String {
            val (email, name) = source
            return "$name\n$email"
        }
    }

}