package com.truongdinh.waiterapp.ui.features.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.truongdinh.waiterapp.ui.theme.AppRadius
import com.truongdinh.waiterapp.domain.model.TableFilter
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun TableFilterBar(
    selected: TableFilter,
    onSelectedChange: (TableFilter) -> Unit,
) {
    val items = listOf(
        TableFilter.ALL to "Tất cả",
        TableFilter.SERVING to "Đang phục vụ",
        TableFilter.EMPTY to "Trống"
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(AppSpacing.sm)
    ) {
        items(items) { (status, label) ->
            FilterChip(
                selected = selected == status,
                onClick = {
                    onSelectedChange(status)
                },
                label = {
                    Text(label)
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary.copy(0.1f),
                    selectedLabelColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(AppRadius.lg)
            )
        }
    }
}