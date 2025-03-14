package ru.easycode.blogpostsintensive.subscriptions

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SubsModule {

    @Binds
    abstract fun bindsRepo(
        repo: BaseSubscriptionRepository,
    ): SubscriptionRepository

}

