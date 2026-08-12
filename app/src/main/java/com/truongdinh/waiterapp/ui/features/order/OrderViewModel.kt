package com.truongdinh.waiterapp.ui.features.order

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truongdinh.waiterapp.data.repository.OrderRepository
import com.truongdinh.waiterapp.data.repository.TableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val tableRepository: TableRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeOrders()
    }

    private fun observeOrders() {
        viewModelScope.launch {
            orderRepository
                .observeOrders()
                .collect { orders ->
                    val orderUiModel = orders.map { order ->
                        val table = tableRepository
                            .getTableById(order.tableId)

                        OrderUiModel(
                            id = order.id,
                            tableName = table?.name ?: "",
                            status = order.status,
                            createAt = order.createdAt
                        )
                    }

                    _uiState.update {
                        it.copy(
                            orders = orderUiModel,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
        }
    }
}