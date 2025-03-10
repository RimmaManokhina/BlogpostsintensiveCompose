package ru.easycode.blogpostsintensive.login


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.ui.theme.BlogpostsintensiveTheme

@Composable
fun LoginButton(
    onClick: () -> Unit
) {
    Box(
        Modifier.fillMaxSize(),
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

@Preview(showBackground = true)
@Composable
fun LoginButtonPreview() {
    BlogpostsintensiveTheme {
        LoginButton({})
    }
}