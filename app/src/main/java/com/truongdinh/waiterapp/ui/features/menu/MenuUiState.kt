package com.truongdinh.waiterapp.ui.features.menu

import com.truongdinh.waiterapp.domain.model.Category
import com.truongdinh.waiterapp.domain.model.MenuItem

data class MenuUiState(
    val tableId: Int = 0,
    val tableName: String = "",
    val totalItems: Int = 0,
    val totalPrices: Long = 0L,
    val menuSearch: String = "",
    val selectedCategory: Int? = null,
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val menuItems: List<MenuItem> = emptyList(),
    val allMenuItems: List<MenuItem> = emptyList(),
    val error: String = ""
)