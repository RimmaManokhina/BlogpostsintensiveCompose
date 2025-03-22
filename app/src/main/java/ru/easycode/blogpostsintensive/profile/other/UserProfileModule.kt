package ru.easycode.blogpostsintensive.profile.other

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.easycode.blogpostsintensive.domain.BlogPost
import ru.easycode.blogpostsintensive.profile.other.data.BaseOtherProfileMapper
import ru.easycode.blogpostsintensive.profile.other.data.OtherProfileRepository
import ru.easycode.blogpostsintensive.profile.other.presentation.BlogPostCard

@Module
@InstallIn(SingletonComponent::class)
abstract class UserProfileModule() {

    @Binds
    abstract fun bindOtherProfileRepository(
        repository: BaseOtherProfileRepository
    ): OtherProfileRepository

    @Binds
    abstract fun bindOtherProfileMapper(
        mapper: BaseOtherProfileMapper
    ): BlogPost.Mapper<BlogPostCard>
}