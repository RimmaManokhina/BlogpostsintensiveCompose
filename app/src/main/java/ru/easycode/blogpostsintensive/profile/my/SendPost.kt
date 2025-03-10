package ru.easycode.blogpostsintensive.profile.my

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
fun SendPost(handlePost : (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {text = it.trim()},
            shape = RoundedCornerShape(10.dp),
            maxLines = 5,
            label = { Text(stringResource(R.string.post_hint)) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                handlePost(text)
                text = ""
            },
            enabled = text.isNotBlank()
        ) {
            Text("send")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SendPostPreview() {
    SendPost(handlePost = {})
}