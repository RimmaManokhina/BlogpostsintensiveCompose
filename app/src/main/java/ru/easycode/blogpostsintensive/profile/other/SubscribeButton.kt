package ru.easycode.blogpostsintensive.profile.other

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.ui.theme.Blue
import ru.easycode.blogpostsintensive.ui.theme.Grey

@Composable
fun SubscribeButton(
    isSubscribedInitial: Boolean,
    onSubscriptionChange: (Boolean) -> Unit
) {
    var isSubscribed by rememberSaveable { mutableStateOf(isSubscribedInitial) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isSubscribed) Blue else Grey,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy), label = ""
    )

    val textColor by animateColorAsState(
        targetValue = if (isSubscribed) Color.White else Color.Black,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy), label = ""
    )

    Box(
        modifier = Modifier
            .height(50.dp)
            .width(160.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                isSubscribed = !isSubscribed
                onSubscriptionChange(isSubscribed)
            },

        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(if (isSubscribed) R.string.unsubscribe else R.string.subscribe),
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSubscribeButton() {
    SubscribeButton(false) { }
}