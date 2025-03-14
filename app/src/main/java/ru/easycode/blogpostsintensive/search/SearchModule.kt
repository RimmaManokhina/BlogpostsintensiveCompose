package ru.easycode.blogpostsintensive.search

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.easycode.blogpostsintensive.search.data.BaseSearchRepository
import ru.easycode.blogpostsintensive.search.data.BaseSearchUserMapper
import ru.easycode.blogpostsintensive.search.data.SearchRepository
import ru.easycode.blogpostsintensive.search.presentation.SearchUserModel

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule() {
    @Binds
    abstract fun bindSearchRepository(
        repository: BaseSearchRepository
    ): SearchRepository

    @Binds
    abstract fun bindSearchUserMapper(
        mapper: BaseSearchUserMapper
    ): SearchUserModel.Mapper<SearchUserCard>
}