package ru.easycode.blogpostsintensive.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.ui.theme.BlogpostsintensiveTheme

@Composable
fun SearchResultUi(
    onClick: () -> Unit,
    userName: String,
    userId: Long,
) {
    Button(
        onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = ButtonColors(
            containerColor = colorResource(R.color.purple_search),
            contentColor = colorResource(R.color.black),
            disabledContainerColor = colorResource(R.color.grey),
            disabledContentColor = colorResource(R.color.black)
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = userName,
                modifier = Modifier.padding(4.dp),
                color = colorResource(R.color.black)
            )

            Text(
                text = "UserId = ${userId.toString()}",
                color = colorResource(R.color.black)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun SearchResultUiPreview() {
    BlogpostsintensiveTheme {
        SearchResultUi({}, "TestName", 897539287523)
    }
}