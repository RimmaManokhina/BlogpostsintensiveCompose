package ru.easycode.blogpostsintensive.subscriptions

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SubsViewModel @Inject constructor(
    repository: SubscriptionRepository,
) : ViewModel() {

    private val _subscribedUsers = MutableStateFlow<List<Subscriber>>(emptyList())
    val subscribedUsers: StateFlow<List<Subscriber>> = _subscribedUsers

    init {
        repository.init { subs ->
            _subscribedUsers.value = (subs)
        }
    }
}

