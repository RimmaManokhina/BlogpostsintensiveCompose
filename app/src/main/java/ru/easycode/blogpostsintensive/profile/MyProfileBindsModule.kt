package ru.easycode.blogpostsintensive.profile

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.easycode.blogpostsintensive.core.UserMapper
import ru.easycode.blogpostsintensive.domain.BlogPost
import ru.easycode.blogpostsintensive.profile.data.BaseMyProfileRepository
import ru.easycode.blogpostsintensive.profile.domain.MyProfileRepository

/**
 * @author DaisyBell on 14.03.2025
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MyProfileBindsModule {

    @Binds
    abstract fun bindsMyProfileRepository(
        myProfileRepository: BaseMyProfileRepository
    ): MyProfileRepository

    @Binds
    abstract fun bindsUserMapper(
        userMapper: UserMapper.Base
    ): UserMapper

    @Binds
    abstract fun bindsBlogPost(
        blogPost: BlogPost.Base
    ): BlogPost

}