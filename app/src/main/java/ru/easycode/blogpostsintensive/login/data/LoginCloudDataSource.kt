package ru.easycode.blogpostsintensive.login.data

import ru.easycode.blogpostsintensive.cloudservice.MyUser
import ru.easycode.blogpostsintensive.cloudservice.Service
import ru.easycode.blogpostsintensive.common.UserProfileCloud
import javax.inject.Inject

interface LoginCloudDataSource {

    suspend fun login()

    class Base @Inject constructor(
        private val myUser: MyUser,
        private val service: Service
    ) : LoginCloudDataSource {

        override suspend fun login() {
            val id = myUser.id()
            val (mail, name) = myUser.userProfileCloud()
            val userProfile = UserProfileCloud(mail, name)
            service.createWithId("users", id, userProfile)
        }
    }
}