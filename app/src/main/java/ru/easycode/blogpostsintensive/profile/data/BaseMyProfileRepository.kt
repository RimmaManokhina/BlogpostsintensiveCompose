package ru.easycode.blogpostsintensive.profile.data

import ru.easycode.blogpostsintensive.cloudservice.MyUser
import ru.easycode.blogpostsintensive.cloudservice.Service
import ru.easycode.blogpostsintensive.common.PostCloud
import ru.easycode.blogpostsintensive.common.UserProfileCloud
import ru.easycode.blogpostsintensive.domain.BlogPost
import ru.easycode.blogpostsintensive.profile.domain.MyProfileRepository
import javax.inject.Inject

class BaseMyProfileRepository @Inject constructor(
    private val service: Service,
    private val myUser: MyUser,
) : MyProfileRepository {

    private var postsCount = 0
    private var lastPost: BlogPost? = null

    override suspend fun init(): List<BlogPost> {
        val myUserId = myUser.id()
        val postClouds = service.get(path = "posts_$myUserId", clasz = PostCloud::class.java)
        postsCount = postClouds.size
        val list = postClouds.map { (postId, postCloud) ->
            val isMyPost = postCloud.ownerId == myUserId
            if (!isMyPost) {
                val userProfile = service.getByQueryValue(
                    "users",
                    UserProfileCloud::class.java
                ).first().second
                userProfile.name
            } else {
                myUser.profile().second
            }
            BlogPost.Base(
                id = postId,
                ownerId = postCloud.ownerId,
                text = postCloud.post,
            )
        }
        if (postClouds.isNotEmpty())
            lastPost = list.first()
        return list
    }

    override fun userName() = myUser.profile()

    override suspend fun createPost(text: String): List<BlogPost> {
        service.postFirstLevelAsync(
            path = "posts_${myUser.id()}",
            obj = PostCloud(
                ownerId = myUser.id(),
                post = text,
            )
        )
        return init()
    }

    override fun logout() {
        myUser.signOut()
    }
}