package com.truongdinh.waiterapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.truongdinh.waiterapp.ui.theme.AppRadius
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun <T> CommonFilterBar(
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T?) -> Unit,
    labelProvider: (T) -> String,
    keyProvider: ((T) -> Any)? = null,
    showAllOption: Boolean = false,
    modifier: Modifier = Modifier
) {
    val chipColors = FilterChipDefaults.filterChipColors(
        selectedContainerColor = MaterialTheme.colorScheme.primary.copy(0.1f),
        selectedLabelColor = MaterialTheme.colorScheme.primary
    )

    val chipShape = remember { RoundedCornerShape(AppRadius.lg) }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = AppSpacing.md, vertical = AppSpacing.sm),
        horizontalArrangement = Arrangement.spacedBy(AppSpacing.sm)
    ) {
        if (showAllOption) {
            item(key = "all_items_generic_key") {
                FilterChip(
                    selected = selectedItem == null,
                    onClick = { onItemSelected(null) },
                    label = { Text("Tất cả") },
                    colors = chipColors,
                    shape = chipShape
                )
            }
        }

        items(
            items = items,
            key = keyProvider?.let { provider -> {
                    item -> provider(item)
                }
            }
        ) { item ->
            val text = remember(item, labelProvider) { labelProvider(item) }

            FilterChip(
                selected = selectedItem == item,
                onClick = {
                    onItemSelected(item)
                },
                label = {
                    Text(text)
                },
                colors = chipColors,
                shape = chipShape
            )

        }
    }
}