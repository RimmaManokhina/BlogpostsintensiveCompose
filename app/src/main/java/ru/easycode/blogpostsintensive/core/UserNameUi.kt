package ru.easycode.blogpostsintensive.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.ui.theme.BlogpostsintensiveTheme

@Composable
fun UserNameUi(
    onClick: (String) -> Unit,
    userName: String,
    userId: String,
) {
    Button(
        onClick = { onClick.invoke(userId) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = ButtonColors(
            containerColor = colorResource(R.color.purple),
            contentColor = colorResource(R.color.black),
            disabledContainerColor = colorResource(R.color.grey),
            disabledContentColor = colorResource(R.color.black)
        )
    ) {
        Text(
            text = userName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            maxLines = 2
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SearchResultUiPreview() {
    BlogpostsintensiveTheme {
        UserNameUi(
            {},
            "TestName Jjgfdkjnfdkjgkjbkd Jjgfdkjnfdkjgkjbkd Jjgfdkjnfdkjgkjbkd fsjbfksjdbfksjdbf",
            "897539287523"
        )
    }
}