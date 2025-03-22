package ru.easycode.blogpostsintensive.profile.other.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.easycode.blogpostsintensive.domain.BlogPost
import ru.easycode.blogpostsintensive.profile.other.data.OtherProfileRepository
import javax.inject.Inject

@HiltViewModel
class OtherProfileViewModel @Inject constructor(
    private val otherProfileRepository: OtherProfileRepository,
    private val mapper: BlogPost.Mapper<BlogPostCard>
) : ViewModel() {

    private val _viewState = MutableStateFlow(OtherProfileViewState())
    val viewState: StateFlow<OtherProfileViewState> = _viewState.asStateFlow()


    fun init(userId: String) {
        viewModelScope.launch {
            try {
                otherProfileRepository.init(userId) { posts ->
                    _viewState.update { currentState ->
                        currentState.copy(
                            posts = posts.map { it.map(mapper) }.asReversed()
                        )
                    }
                }
                _viewState.update { currentState ->
                    currentState.copy(
                        isSubscribed = otherProfileRepository.isSubscribed(userId),
                    )
                }
            } catch (e: Exception) {
                // TODO: error handling
            }
        }
    }


    fun updateSubscription(userId: String) {
        viewModelScope.launch {
            try {
                val success = if (viewState.value.isSubscribed == true) {
                    otherProfileRepository.unsubscribe(userId)
                } else {
                    otherProfileRepository.subscribe(userId)
                }
                if (success) {
                    _viewState.update { currentState ->
                        currentState.copy(
                            isSubscribed = !(currentState.isSubscribed ?: false)
                        )
                    }
                }
            } catch (e: Exception) {
                // TODO: error handling
            }
        }
    }

}

data class OtherProfileViewState(
    val isSubscribed: Boolean? = null,
    val posts: List<BlogPostCard> = emptyList(),
)