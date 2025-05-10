package ru.easycode.blogpostsintensive.profile.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.easycode.blogpostsintensive.core.BaseViewModel
import ru.easycode.blogpostsintensive.core.RunAsync
import ru.easycode.blogpostsintensive.core.UserMapper
import ru.easycode.blogpostsintensive.domain.BlogPost
import ru.easycode.blogpostsintensive.profile.data.ShareInfo
import ru.easycode.blogpostsintensive.profile.domain.CreatePost
import ru.easycode.blogpostsintensive.profile.domain.MyProfileRepository
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val repository: MyProfileRepository,
    private val userMapper: UserMapper,
    private val postMapper: BlogPost.Mapper<MyPost>,
    runAsync: RunAsync,
) : BaseViewModel(runAsync), CreatePost, SendAndHideEdit, PostActions {

    private val _viewState = MutableStateFlow<List<MyPost>>(emptyList())
    val viewState: StateFlow<List<MyPost>> = _viewState

    private val showUi: (List<BlogPost>) -> Unit = { posts ->
        _viewState.value = (posts.map { it.map(postMapper) }.asReversed())
    }

    private val _editState = MutableStateFlow<SendPostUiState>(SendPostUiState.Create(this))
    val editState: StateFlow<SendPostUiState> = _editState

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

    override fun editPost(id: String, message: String) {
        _editState.value = SendPostUiState.Edit(id, message, this)
    }

    override fun createPost(text: String) =
        runAsync({
            repository.createPost(text)
        }, showUi)

    override fun handleSendEdited(postId: String, text: String) {
        runAsync({
            repository.edit(postId, text)
            repository.init()
        }, {
            showUi.invoke(it)
            hideEdit()
        })
    }

    override fun hideEdit() {
        _editState.value = SendPostUiState.Create(this)
    }

    override fun deletePost(postId: String) =
        runAsync({
            repository.deletePost(postId)
        }, showUi)

    override fun shareInfo(blogPostUi: BlogPostUi): ShareInfo {
        return ShareInfo(userInfo(), blogPostUi.message())
    }
}

class BaseBlogPostMapper @Inject constructor() : BlogPost.Mapper<MyPost> {

    override fun toBase(id: String, ownerId: String, text: String) = MyPost.Base(
        BlogPostUi.Base(message = text),
        postId = id,
    )

}

interface SendEdited {
    fun handleSendEdited(postId: String, text: String)
}

interface HideEdit {
    fun hideEdit()
}

interface SendAndHideEdit : SendEdited, HideEdit

interface EditPost {
    fun editPost(id: String, message: String)
}

interface DeletePost {
    fun deletePost(postId: String)
}

interface SharePost {
    fun shareInfo(blogPostUi: BlogPostUi): ShareInfo

}

interface PostActions : EditPost, DeletePost, SharePost {

    // only for Preview
    object Empty : PostActions {
        override fun editPost(id: String, message: String) = Unit

        override fun deletePost(postId: String) = Unit

        override fun shareInfo(blogPostUi: BlogPostUi): ShareInfo {
            return ShareInfo("", "")
        }
    }
}