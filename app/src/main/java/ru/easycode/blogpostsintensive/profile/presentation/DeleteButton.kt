package ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.profile.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun DeleteButton(onDeleteClick: () -> Unit) {
    IconButton(onClick = onDeleteClick) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Post",
            tint = MaterialTheme.colorScheme.error
        )
    }
}