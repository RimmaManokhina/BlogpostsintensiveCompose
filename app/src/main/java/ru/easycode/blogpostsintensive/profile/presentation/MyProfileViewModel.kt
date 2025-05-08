package ru.easycode.blogpostsintensive.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.easycode.blogpostsintensive.core.BaseViewModel
import ru.easycode.blogpostsintensive.core.RunAsync
import ru.easycode.blogpostsintensive.core.UserMapper
import ru.easycode.blogpostsintensive.domain.BlogPost
import ru.easycode.blogpostsintensive.profile.domain.CreatePost
import ru.easycode.blogpostsintensive.profile.domain.MyProfileRepository
import ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.profile.presentation.DeleteButton
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val repository: MyProfileRepository,
    private val userMapper: UserMapper,
    private val postMapper: BlogPost.Mapper<MyPost>,
    runAsync: RunAsync,
) : BaseViewModel(runAsync), CreatePost {

    private val _viewState = MutableStateFlow<List<MyPost>>(emptyList())
    val viewState: StateFlow<List<MyPost>> = _viewState

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

    fun deletePost(postId: String) =
        runAsync({
            repository.deletePost(postId)
        }, showUi)
}

class BaseBlogPostMapper @Inject constructor() : BlogPost.Mapper<MyPost> {

    override fun toBase(id: String, ownerId: String, text: String) = MyPost.Base(
        BlogPostUi.Base(
            id = id,
            ownerId = ownerId,
            message = text,
        )
    )

}


interface MyPost {

    @Composable
    fun Show(deletePost: (String) -> Unit)

    class Base(
        private val blogPostUi: BlogPostUi,
    ) : MyPost {

        @Composable
        override fun Show(deletePost: (String) -> Unit) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    blogPostUi.Show()
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    )
                    {
                        DeleteButton(onDeleteClick = {
                            deletePost(blogPostUi.id())
                        })
                    }
                }
            }
        }
    }
}





