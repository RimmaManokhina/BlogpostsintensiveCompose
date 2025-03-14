package ru.easycode.blogpostsintensive.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.easycode.blogpostsintensive.search.SearchUserCard
import ru.easycode.blogpostsintensive.search.data.SearchRepository
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val mapper: SearchUserModel.Mapper<SearchUserCard>
) : ViewModel() {

    private val _foundUsers = MutableStateFlow<List<SearchUserCard>>(emptyList())
    val foundUsers: StateFlow<List<SearchUserCard>> = _foundUsers.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun findUsers(mail: String) {
        when {
            mail.length < 3 -> {
                _foundUsers.value = emptyList()
                _isLoading.value = false
            }

            else -> viewModelScope.launch {
                _isLoading.value = true
                _foundUsers.value = searchRepository.findUsers(mail).map { it.map(mapper) }
                _isLoading.value = false
            }
        }
    }
}