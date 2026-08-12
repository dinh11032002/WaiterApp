package com.truongdinh.waiterapp.ui.features.home

import com.truongdinh.waiterapp.data.local.entity.Shift
import com.truongdinh.waiterapp.domain.model.Table
import com.truongdinh.waiterapp.domain.model.TableFilter

data class HomeUiState(
    val query: String = "",
    val username: String? = null,
    val shift: Shift? = null,
    val selected: TableFilter? = TableFilter.ALL,
    val tables: List<Table> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)