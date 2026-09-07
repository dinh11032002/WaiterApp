package com.truongdinh.waiterapp.ui.features.order.detail

import android.R
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truongdinh.waiterapp.data.repository.MenuItemRepository
import com.truongdinh.waiterapp.data.repository.OrderItemRepository
import com.truongdinh.waiterapp.data.repository.OrderRepository
import com.truongdinh.waiterapp.data.repository.StaffRepository
import com.truongdinh.waiterapp.data.repository.TableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val menuItemRepository: MenuItemRepository,
    private val staffRepository: StaffRepository,
    private val tableRepository: TableRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val orderId: Int = checkNotNull(savedStateHandle["orderId"])

    private val _uiState = MutableStateFlow(OrderDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeOrderDetail()
    }

    private fun observeOrderDetail() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                combine(
                    orderRepository.getOrderById(orderId),
                    orderItemRepository.getOrderItemsByOrderId(orderId),
                    menuItemRepository.getMenuItems()
                ) { order, orderItems, menuItems ->
                    if (order == null) return@combine null

                    val table = tableRepository.getTableById(order.tableId)
                    val staff = staffRepository.getStaffById(order.staffId)

                    val orderItemUiModels = orderItems.mapNotNull { item ->
                        val menuItem = menuItems.find {
                            it.id == item.menuItemId
                        }
                            ?: return@mapNotNull null
                        OrderItemUiModel(
                            orderItemId = item.id,
                            menuItemId = item.menuItemId,
                            name = menuItem.name,
                            quantity = item.quantity,
                            unitPrice = item.unitPrice,
                            image = menuItem.image
                        )
                    }

                    OrderDetailUiModel(
                        orderId = order.id,
                        tableName = table?.name.orEmpty(),
                        staffName = "Nhân viên: ${staff?.fullName ?: "Không rõ"}",
                        status = order.status,
                        createdAt = order.createdAt,
                        orderItemUiModel = orderItemUiModels,
                        staffId = order.staffId,
                        tableId = table?.id ?: 0
                    )
                }.collect { uiModel ->
                    if (uiModel == null) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                messageError = "Không tìm thấy đơn hàng"
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                orderDetailUiModel = uiModel,
                                isLoading = false,
                                messageError = null
                            )
                        }
                    }
                }
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        messageError = exception.message ?: "Không thể tải đơn hàng"
                    )
                }
            }
        }
    }
}