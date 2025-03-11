package ru.easycode.blogpostsintensive.post

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.ui.theme.BlogpostsintensiveTheme

/**
 * @author DaisyBell on 10.03.2025
 */
@Composable
fun PostContent(text: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var needsExpansion by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = text,
                fontSize = 18.sp,
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                onTextLayout = { layoutResult: TextLayoutResult ->
                    needsExpansion = layoutResult.hasVisualOverflow
                }
            )
            if (needsExpansion || expanded) {
                Text(
                    text = stringResource(if (expanded) R.string.less else R.string.more),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                        .fillMaxWidth()
                        .padding(end = 8.dp)
                )
            }
        }
    }
}

class TextPreviewProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "Short Text",
        "A bit longer text. A bit longer text. A bit longer text. A bit longer text.",
        "This one is really, really long. Like, really long!" +
                "This one is really, really long. Like, really long!" +
                "This one is really, really long. Like, really long!" +
                "This one is really, really long. Like, really long!" +
                "This one is really, really long. Like, really long!" +
                "This one is really, really long. Like, really long!"
    )
}

@Preview(
    name = "Default",
    group = "Default",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_7_PRO
)
@Preview(
    name = "Night theme",
    group = "Night theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_7_PRO
)
@Preview(
    name = "Small screen",
    group = "Small screen",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 300,
    heightDp = 500
)
annotation class ScreenPreview

@ScreenPreview
@Composable
fun PreviewPostContent(
    @PreviewParameter(TextPreviewProvider::class) text: String
) {
    BlogpostsintensiveTheme {
        PostContent(text = text)
    }
}