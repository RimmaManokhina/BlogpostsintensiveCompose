package ru.easycode.blogpostsintensive.search.data

import ru.easycode.blogpostsintensive.cloudservice.MyUser
import ru.easycode.blogpostsintensive.cloudservice.Service
import ru.easycode.blogpostsintensive.common.UserProfileCloud
import ru.easycode.blogpostsintensive.search.presentation.SearchUserModel
import javax.inject.Inject


class BaseSearchRepository @Inject constructor(
    private val myUser: MyUser,
    private val service: Service
) : SearchRepository {

    override suspend fun findUsers(userEmail: String): List<SearchUserModel> {
        val result = service.getByQueryStartWith(
            "users",
            "mail",
            userEmail,
            UserProfileCloud::class.java
        )

        return result
            .filter { it.second.mail.startsWith(userEmail, true) && it.first != myUser.id() }
            .map { SearchUserModel.Base(it.first, it.second.name, it.second.mail) }
    }
}