package com.truongdinh.waiterapp.ui.features.menu.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.truongdinh.waiterapp.domain.model.MenuItem
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun MenuList(
    menuItems: List<MenuItem>,
    onMenuItemClick: (MenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
    if (menuItems.isEmpty()) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Không có đồ uống",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.md),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.md)
        ) {
            items(
                items = menuItems,
                key = { it.id }
            ) { item ->
                MenuItemCard(
                    menuItem = item,
                    onMenuItemClick = {
                        onMenuItemClick(item)
                    }
                )
            }
        }
    }
}