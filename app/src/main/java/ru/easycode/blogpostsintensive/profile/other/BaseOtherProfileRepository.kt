package ru.easycode.blogpostsintensive.profile.other

import android.util.Log
import ru.easycode.blogpostsintensive.cloudservice.MyUser
import ru.easycode.blogpostsintensive.cloudservice.Service
import ru.easycode.blogpostsintensive.common.PostCloud
import ru.easycode.blogpostsintensive.common.UserCloud
import ru.easycode.blogpostsintensive.common.UserProfileCloud
import ru.easycode.blogpostsintensive.domain.BlogPost
import ru.easycode.blogpostsintensive.profile.other.data.OtherProfileRepository
import javax.inject.Inject


class BaseOtherProfileRepository @Inject constructor(
    private val service: Service,
    private val myUser: MyUser,
) : OtherProfileRepository {

    override suspend fun subscribe(userId: String): Boolean = try {
        service.postFirstLevel("subs_${myUser.id()}", UserCloud(userId))
        true
    } catch (e: Exception) {
        false
    }

    override suspend fun isSubscribed(userId: String): Boolean {
        return try {
            service.getByQuery(
                "subs_${myUser.id()}",
                "ownerId",
                userId,
                UserCloud::class.java
            ).isNotEmpty()
        } catch (e: Exception) {
            false
        }
    }

    override fun init(
        userId: String,
        callback: (posts: List<BlogPost>) -> Unit,
    ) {
        service.startListen(
            path = "posts_$userId",
            clasz = PostCloud::class.java,
            callback = object : Service.Callback<PostCloud> {
                override fun provide(obj: List<Pair<String, PostCloud>>) {
                    callback(obj.map { it.toBlogPost() })
                }

                override fun error(message: String) {
                    // TODO: error handling
                }
            }
        )
    }

    override suspend fun unsubscribe(userId: String): Boolean = try {
        val list = service.getByQuery(
            "subs_${myUser.id()}",
            "ownerId",
            userId,
            UserCloud::class.java
        )
        if (list.isNotEmpty()) {
            service.remove("subs_${myUser.id()}", list[0].first)
            true
        } else false
    } catch (e: Exception) {
        false
    }

    override suspend fun userProfile(userId: String): Pair<String, String> {
        return try {
            Log.d("dsb98", "userProfile/userId: $userId")
            val userInfo = service.getByQueryValue(
                "users",
                UserProfileCloud::class.java
            ).first().second
            Pair(userInfo.name, userInfo.mail)
        } catch (e: Exception) {
            Pair("", "")
        }
    }
}

private fun Pair<String, PostCloud>.toBlogPost() = BlogPost.Base(
    text = second.post,
    ownerId = second.ownerId,
    id = first
)
