package ru.easycode.blogpostsintensive.profile.presentation

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.easycode.blogpostsintensive.R

@Composable
fun EditButton(editClick: () -> Unit) {
    IconButton(onClick = editClick) {
        Icon(
            painter = painterResource(id = R.drawable.edit_24),
            contentDescription = stringResource(R.string.edit_post),
            tint = MaterialTheme.colorScheme.error
        )
    }
}