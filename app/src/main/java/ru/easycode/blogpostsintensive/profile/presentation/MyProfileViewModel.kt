package ru.easycode.blogpostsintensive.profile.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.easycode.blogpostsintensive.core.BaseViewModel
import ru.easycode.blogpostsintensive.core.RunAsync
import ru.easycode.blogpostsintensive.core.UserMapper
import ru.easycode.blogpostsintensive.domain.BlogPost
import ru.easycode.blogpostsintensive.profile.domain.CreatePost
import ru.easycode.blogpostsintensive.profile.domain.MyProfileRepository
import javax.inject.Inject

@HiltViewModel
internal class MyProfileViewModel @Inject constructor(
    private val repository: MyProfileRepository,
    private val userMapper: UserMapper,
    private val postMapper: BlogPost.Mapper<BlogPostUi>,
    runAsync: RunAsync,
) : BaseViewModel(runAsync), CreatePost {

    private val _viewState = MutableStateFlow<List<BlogPostUi>>(emptyList())
    val viewState: StateFlow<List<BlogPostUi>> = _viewState

    private val showUi: (List<BlogPost>) -> Unit = { posts ->
        _viewState.value = (posts.map { it.map(postMapper) }.asReversed())
    }

    init {
        runAsync({
            repository.init()
        }, showUi)
    }

    fun logout() {
        repository.logout()
    }

    fun profile() = repository.userName()

    fun userInfo() = userMapper.map(profile())

    override fun createPost(text: String) =
        runAsync({
            repository.createPost(text)
        }, showUi)

}

class BaseBlogPostMapper @Inject constructor() : BlogPost.Mapper<BlogPostUi> {

    override fun toBase(text: String) = BlogPostUi.Base(
        message = text,
    )
}