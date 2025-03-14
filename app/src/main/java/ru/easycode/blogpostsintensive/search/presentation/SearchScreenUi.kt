package ru.easycode.blogpostsintensive.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = hiltViewModel()
    val users by viewModel.foundUsers.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchUserField { query ->
            viewModel.findUsers(query)
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(users.size) { index ->
                val user = users[index]
                user.Show(onClick = { userId -> /* Handle click event here */ })
            }
        }
    }
}