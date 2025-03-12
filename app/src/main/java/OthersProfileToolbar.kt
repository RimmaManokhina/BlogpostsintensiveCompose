package ru.easycode.blogpostsintensive.profile.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.ui.theme.BlogpostsintensiveTheme

@Composable
fun OthersProfileToolbar(name: String, onBackClick: () -> Unit) {
    Box(
        Modifier
            .background(colorResource(R.color.white))
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow Back"
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                color = colorResource(R.color.black),
                textAlign = TextAlign.Start,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun OthersProfileToolbarPreview() {
    BlogpostsintensiveTheme {
        OthersProfileToolbar(
            name = "User Name User Name User Name User Name User Name name = \"User Name User Name User Name User Name User Name",
            onBackClick = { }
        )
    }
}