package ru.easycode.blogpostsintensive.subscriptions

import ru.easycode.blogpostsintensive.cloudservice.MyUser
import ru.easycode.blogpostsintensive.cloudservice.Service
import ru.easycode.blogpostsintensive.common.UserProfileCloud
import javax.inject.Inject

class BaseSubscriptionRepository @Inject constructor(
    private val service: Service,
    private val myUser: MyUser,
) : SubscriptionRepository {

    @Volatile
    private var subscribersList = mutableListOf<Subscriber>()

    override fun init(callback: (subs: List<Subscriber>) -> Unit) {
        service.startListen(
            path = "subs_${myUser.id()}",
            clasz = SubscriberCloud::class.java,
            object : Service.Callback<SubscriberCloud> {
                override fun provide(obj: List<Pair<String, SubscriberCloud>>) {
                    clearSubscribersList()
                    handle(obj, callback)
                }

                override fun error(message: String) {

                }
            }
        )
    }

    private fun clearSubscribersList() = synchronized(lock) {
        subscribersList = mutableListOf()
    }

    private fun handle(
        obj: List<Pair<String, SubscriberCloud>>,
        callback: (subs: List<Subscriber>) -> Unit,
    ) {
        val size = obj.size
        if (size == 0) {
            callback.invoke(emptyList())
        } else {
            obj.forEach {
                userName(it.second.ownerId, callback, size)
            }
        }
    }

    private fun userName(
        userId: String,
        callback: (subs: List<Subscriber>) -> Unit,
        size: Int,
    ) {
        service.getByQueryAsync(
            path = "users",
            queryValue = userId,
            clasz = UserProfileCloud::class.java,
            callback = object : Service.Callback<UserProfileCloud> {
                override fun provide(obj: List<Pair<String, UserProfileCloud>>) {
                    val subscriber = obj.map {
                        Subscriber(id = it.first, name = it.second.name)
                    }
                    dispatchSubscriber(subscriber, callback, size)
                }

                override fun error(message: String) {

                }
            }
        )
    }

    private fun dispatchSubscriber(
        subscriber: List<Subscriber>,
        callback: (subs: List<Subscriber>) -> Unit,
        size: Int,
    ) = synchronized(lock = lock) {
        subscribersList.addAll(subscriber)
        if (subscribersList.size == size) {
            callback.invoke(subscribersList)
        }
    }

    companion object {
        private val lock = Object()
    }
}