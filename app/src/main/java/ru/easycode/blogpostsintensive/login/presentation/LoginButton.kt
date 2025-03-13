package ru.easycode.blogpostsintensive.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.presentation.ScreenPreview
import ru.easycode.blogpostsintensive.ui.theme.BlogpostsintensiveTheme

@Composable
fun LoginButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick,
        ) {
            Text(
                text = stringResource(R.string.login)
            )
        }
    }
}

@ScreenPreview
@Composable
fun LoginButtonPreview() {
    BlogpostsintensiveTheme {
        LoginButton(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {}
    }
}