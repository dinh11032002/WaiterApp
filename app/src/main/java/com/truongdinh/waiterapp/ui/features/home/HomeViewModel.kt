package com.truongdinh.waiterapp.ui.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truongdinh.waiterapp.data.local.session.SessionManager
import com.truongdinh.waiterapp.data.repository.TableRepository
import com.truongdinh.waiterapp.domain.model.Table
import com.truongdinh.waiterapp.domain.model.TableFilter
import com.truongdinh.waiterapp.domain.model.TableStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TableRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState()
    )

    val uiState = _uiState.asStateFlow()

    private var allTables: List<Table> = emptyList()

    companion object {
        private const val TAG = "HomeViewModel"
    }

    init {
        Log.d(TAG, "ViewModel Init")

        syncTables()
        observeTables()

        viewModelScope.launch {
            sessionManager.session.collect { session ->
                _uiState.update {
                    it.copy(
                        username = session.staffName,
                        shift = session.shift
                    )
                }
            }
        }
    }

    private fun observeTables() {
        repository.getTables()
            .onEach { tables ->
                Log.d(
                    TAG,
                    "Observe tables: ${tables.size}"
                )

                allTables = tables

                _uiState.update {
                    it.copy(
                        tables = tables
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun filterTablesByStatus(tables: List<Table>, filter: TableFilter?): List<Table> {
        val selected = _uiState.value.selected

        Log.d(
            TAG,
            "Selected Status: $selected"
        )

        if (filter == null || filter == TableFilter.ALL) {
            return tables
        }

        return tables.filter { table ->
            when (filter) {
                TableFilter.EMPTY -> table.status == TableStatus.EMPTY
                TableFilter.SERVING -> table.status == TableStatus.SERVING
                else -> true
            }
        }
    }

    fun onSelectedChange(status: TableFilter?) {
        _uiState.update { currentState ->
            currentState.copy(
                selected = status,
                tables = filterTablesByStatus(allTables, status)
            )
        }
    }

    fun onQueryChange(query: String) {
        _uiState.update {
            it.copy(
                query = query
            )
        }

        if (query.isBlank()) {
            _uiState.update {
                it.copy(
                    tables = allTables
                )
            }
            return
        }

        val searchQuery = if (query.trim().all { it.isDigit() }) {
            "Bàn $query"
        } else {
            query
        }

        repository.searchTables(searchQuery).onEach { tables ->
            _uiState.update {
                it.copy(
                    tables = tables
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun syncTables() {
        viewModelScope.launch {
            repository.syncTables()
        }
    }

    fun updateTableStatus(tableId: Int, status: TableStatus) {
        viewModelScope.launch {
            repository.updateTableStatus(tableId, status)

            _uiState.update { currentState ->
                currentState.copy(
                    tables = currentState.tables.map { table ->
                        if (table.id == tableId) {
                            table.copy(status = status)
                        } else {
                            table
                        }
                    }
                )
            }
        }
    }
}