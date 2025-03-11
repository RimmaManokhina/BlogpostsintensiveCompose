package ru.easycode.blogpostsintensive.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.easycode.blogpostsintensive.R

@Composable
fun SearchUser(onUserSearch: (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onUserSearch.invoke(it)
            },
            shape = RoundedCornerShape(10.dp),
            maxLines = 1,
            label = { Text(stringResource(R.string.enter_username)) },
            modifier = Modifier.weight(1f),
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(onClick = { text = "" }) {
                        Icon(imageVector = Icons.Default.Clear, null)
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchUser() {
    SearchUser() {}
}