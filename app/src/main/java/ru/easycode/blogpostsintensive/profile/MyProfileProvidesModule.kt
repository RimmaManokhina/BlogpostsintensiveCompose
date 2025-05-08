package ru.easycode.blogpostsintensive.profile

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.easycode.blogpostsintensive.domain.BlogPost
import ru.easycode.blogpostsintensive.profile.presentation.BaseBlogPostMapper
import ru.easycode.blogpostsintensive.profile.presentation.MyPost

/**
 * @author DaisyBell on 14.03.2025
 */
@Module
@InstallIn(SingletonComponent::class)
object MyProfileProvidesModule {

    @Provides
    fun provideBlogPostMapper(): BlogPost.Mapper<MyPost> = BaseBlogPostMapper()
}