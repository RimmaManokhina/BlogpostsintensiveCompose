package ru.easycode.blogpostsintensive.core

import javax.inject.Inject

interface UserMapper: Mapper<Pair<String, String>, String> {

    class Base @Inject constructor() : UserMapper {
        override fun map(source: Pair<String, String>): String {
            val (email, name) = source
            return "$name\n$email"
        }
    }

}