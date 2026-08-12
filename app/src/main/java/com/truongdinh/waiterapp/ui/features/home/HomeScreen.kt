package com.truongdinh.waiterapp.ui.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.truongdinh.waiterapp.component.CommonFilterBar
import com.truongdinh.waiterapp.component.SearchBar
import com.truongdinh.waiterapp.data.local.entity.Shift
import com.truongdinh.waiterapp.ui.features.home.component.HomeHeader
import com.truongdinh.waiterapp.ui.features.home.component.TableGrid
import com.truongdinh.waiterapp.ui.theme.AppSpacing
import com.truongdinh.waiterapp.ui.theme.WaiterAppTheme
import com.truongdinh.waiterapp.domain.model.Table
import com.truongdinh.waiterapp.domain.model.TableFilter
import com.truongdinh.waiterapp.domain.model.TableStatus

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onSelectedChange: (TableFilter?) -> Unit,
    onTableClick: (Table) -> Unit,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            HomeHeader(
                username = uiState.username,
                shift = uiState.shift,
                modifier = Modifier.padding(horizontal = AppSpacing.lg)
            )
        }
    ) { innerPadding ->
        Column(
            modifier
                .padding(innerPadding)
                .padding(horizontal = AppSpacing.lg)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.padding(AppSpacing.lg))

            SearchBar(
                query = uiState.query,
                onQueryChange = onQueryChange,
                placeholderText = "Tìm kiếm bàn...",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(AppSpacing.md))

            CommonFilterBar(
                items = remember { TableFilter.values().toList() },
                selectedItem = uiState.selected,
                onItemSelected = onSelectedChange,
                labelProvider = { filter ->
                    when (filter) {
                        TableFilter.ALL -> "Tất cả"
                        TableFilter.SERVING -> "Đang phục vụ"
                        TableFilter.EMPTY -> "Trống"
                    }
                },
                keyProvider = { filter ->
                    filter.name
                }
            )

            Spacer(modifier = Modifier.height(AppSpacing.sm))

            TableGrid(
                tables = uiState.tables,
                onTableClick = onTableClick,
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_7_pro"
)
@Composable
fun HomePreview() {
    WaiterAppTheme {
        HomeScreen(
            uiState = HomeUiState(
                query = "",
                username = "Trương Đình",
                selected = TableFilter.ALL,
                shift = Shift.MORNING,
                tables = listOf(
                    Table(1, "Bàn 1", TableStatus.EMPTY),
                    Table(2, "Bàn 2", TableStatus.SERVING),
                    Table(3, "Bàn 3", TableStatus.EMPTY)
                )
            ),
            onSelectedChange = {},
            onTableClick = {},
            onQueryChange = {},
        )
    }
}