package ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.profile.presentation

import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.profile.data.ShareInfo

@Composable
fun ShareButton(shareInfo: ShareInfo) {
    val context = LocalContext.current
    IconButton(onClick = {
        val sharedIntent = Intent(Intent.ACTION_SEND)
        sharedIntent.type = "text/plain"
        sharedIntent.putExtra(Intent.EXTRA_TEXT, shareInfo.text())
        val chooser = Intent.createChooser(sharedIntent, "Share")
        context.startActivity(chooser)
    }) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = stringResource(R.string.share_post),
            tint = MaterialTheme.colorScheme.error
        )
    }
}