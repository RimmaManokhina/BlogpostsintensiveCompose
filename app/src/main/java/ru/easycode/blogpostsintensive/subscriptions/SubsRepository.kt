package ru.easycode.blogpostsintensive.subscriptions

interface SubscriptionRepository {

    fun init(callback: (subs: List<Subscriber>) -> Unit)
}

