package ru.easycode.blogpostsintensive.profile.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.ui.theme.Blue

@Composable
fun EditPost(message: String, hideEdit: () -> Unit, handlePost: (String) -> Unit) {

    Column(
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(16.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.edit_24),
                stringResource(id = R.string.send),
                alignment = Alignment.CenterStart
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = message,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = R.drawable.close_24),
                stringResource(id = R.string.send),
                Modifier.clickable {
                    hideEdit.invoke()
                }
            )
        }

        InputText(message, handlePost)
    }
}

@Composable
fun InputText(message: String, handlePost: (String) -> Unit) {
    var text by remember { mutableStateOf(message) }

    LaunchedEffect(message) {
        text = message
    }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            shape = RoundedCornerShape(10.dp),
            maxLines = 5,
            label = { Text(stringResource(R.string.post_hint)) },
            modifier = Modifier.weight(1f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Blue,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Blue
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                handlePost(text.trim())
                text = ""
            },
            enabled = text.isNotBlank(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
                contentColor = Color.White
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.check_mark),
                contentDescription = stringResource(id = R.string.send),
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center
            )
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun EditPostPreview() {
    EditPost(
        message = "PostText PostText PostText PostText PostText " +
            "PostText PostText PostText PostText PostText " +
            "PostText PostText PostText PostText PostText " +
            "PostText PostText PostText PostText PostText ",
        handlePost = {},
        hideEdit = {}
    )
}


