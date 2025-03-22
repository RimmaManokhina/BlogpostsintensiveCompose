package ru.easycode.blogpostsintensive.profile.other.data

import ru.easycode.blogpostsintensive.domain.BlogPost

interface OtherProfileRepository {

    suspend fun subscribe(userId: String): Boolean

    suspend fun unsubscribe(userId: String): Boolean

    suspend fun isSubscribed(userId: String): Boolean

    fun init(userId: String, callback: (posts: List<BlogPost>) -> Unit)

    suspend fun userProfile(userId: String): Pair<String, String>

}