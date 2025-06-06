package ru.easycode.blogpostsintensive.profile.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.easycode.blogpostsintensive.R

@Composable
fun DeleteButton(onDeleteClick: () -> Unit) {
    IconButton(onClick = onDeleteClick) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.delete_post),
            tint = MaterialTheme.colorScheme.error
        )
    }
}