package com.truongdinh.waiterapp.ui.features.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.truongdinh.waiterapp.domain.model.Table
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun TableGrid(
    tables: List<Table>,
    onTableClick: (Table) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(AppSpacing.md),
        horizontalArrangement = Arrangement.spacedBy(AppSpacing.md),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.md),
        modifier = modifier
    ) {
        items(tables) { table ->
            TableItem(
                table = table,
                onClick = onTableClick
            )
        }
    }
}