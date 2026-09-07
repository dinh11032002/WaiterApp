package com.truongdinh.waiterapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.truongdinh.waiterapp.ui.theme.AppSize
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun CommonHeader(
    title: String,
    onBackClick: (() -> Unit)? = null,
    titleAlignment: HeaderTitleAlignment = HeaderTitleAlignment.Center,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(AppSize.topBarHeight),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (titleAlignment == HeaderTitleAlignment.Start) {
            Arrangement.Start
        } else {
            Arrangement.Center
        }
    ) {
        if (onBackClick != null) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "icon_back"
                )
            }
        } else if (titleAlignment == HeaderTitleAlignment.Center) {
            Spacer(modifier = Modifier.width(48.dp))
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = if (titleAlignment == HeaderTitleAlignment.Center) TextAlign.Center else TextAlign.Start,
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = if (titleAlignment == HeaderTitleAlignment.Start && onBackClick != null) {
                        AppSpacing.md
                    } else {
                        0.dp
                    }
                )
        )

        if (titleAlignment == HeaderTitleAlignment.Center) {
            Spacer(modifier = Modifier.width(48.dp))
        }
    }
}