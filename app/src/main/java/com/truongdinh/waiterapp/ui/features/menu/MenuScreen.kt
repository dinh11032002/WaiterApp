package com.truongdinh.waiterapp.ui.features.menu

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
import com.truongdinh.waiterapp.component.CommonHeader
import com.truongdinh.waiterapp.component.SearchBar
import com.truongdinh.waiterapp.data.source.CategorySource
import com.truongdinh.waiterapp.data.source.MenuItemSource
import com.truongdinh.waiterapp.domain.model.MenuItem
import com.truongdinh.waiterapp.ui.features.menu.component.BottomCartBar
import com.truongdinh.waiterapp.ui.features.menu.component.MenuList
import com.truongdinh.waiterapp.ui.theme.AppSpacing
import com.truongdinh.waiterapp.ui.theme.WaiterAppTheme

@Composable
fun MenuScreen(
    uiState: MenuUiState,
    onMenuSearchChange: (String) -> Unit,
    onSelectedChange: (Int?) -> Unit,
    onMenuItemClick: (MenuItem) -> Unit,
    onBottomCartBarClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CommonHeader(
                title = "Menu",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            BottomCartBar(
                tableName = uiState.tableName,
                totalItems = uiState.totalItems,
                totalPrices = uiState.totalPrices,
                onBottomCartBarClick = onBottomCartBarClick
            )
        }
    ) { innerPadding ->
       Column(
           modifier = modifier
               .padding(paddingValues = innerPadding)
               .padding(horizontal = AppSpacing.lg)
               .fillMaxSize()
       ) {
           SearchBar(
               query = uiState.menuSearch,
               onQueryChange = onMenuSearchChange,
               placeholderText = "Tìm kiếm đồ uống...",
               modifier = Modifier.fillMaxWidth()
           )

           Spacer(modifier = Modifier.height(AppSpacing.md))

           CommonFilterBar(
               items = uiState.categories,
               selectedItem = remember(uiState.categories, uiState.selectedCategory) {
                   uiState.categories.find { it.id == uiState.selectedCategory }
               },
               onItemSelected = { category ->
                   onSelectedChange(category?.id)
               },
               labelProvider = { category ->
                    category.name
               },
               keyProvider = { category -> category.id },
               showAllOption = true
           )

           Spacer(modifier = Modifier.height(AppSpacing.md))

           MenuList(
               menuItems = uiState.menuItems,
               onMenuItemClick = onMenuItemClick,
               modifier = Modifier
                   .weight(1f)
                   .fillMaxWidth()
           )
       }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_7_pro")
@Composable
fun MenuPreview() {
    WaiterAppTheme {
        MenuScreen(
            uiState = MenuUiState(
                categories = CategorySource.categories,
                selectedCategory = null,
                menuItems = MenuItemSource.menuItems,
                tableName = "Bàn 1",
                totalItems = 3,
                totalPrices = 105000,
                tableId = 1
            ),
            onBackClick = {},
            onMenuSearchChange = {},
            onSelectedChange = {},
            onMenuItemClick = {},
            onBottomCartBarClick = {}
        )
    }
}