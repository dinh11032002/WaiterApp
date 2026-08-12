package com.truongdinh.waiterapp.ui.features.cart

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truongdinh.waiterapp.data.local.session.SessionManager
import com.truongdinh.waiterapp.data.repository.CartRepository
import com.truongdinh.waiterapp.data.repository.MenuItemRepository
import com.truongdinh.waiterapp.data.repository.OrderItemRepository
import com.truongdinh.waiterapp.data.repository.OrderRepository
import com.truongdinh.waiterapp.data.repository.TableRepository
import com.truongdinh.waiterapp.domain.model.Cart
import com.truongdinh.waiterapp.domain.model.MenuItem
import com.truongdinh.waiterapp.domain.model.Order
import com.truongdinh.waiterapp.domain.model.OrderItem
import com.truongdinh.waiterapp.domain.model.OrderStatus
import com.truongdinh.waiterapp.domain.model.TableStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val menuItemRepository: MenuItemRepository,
    private val tableRepository: TableRepository,
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val sessionManager: SessionManager,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(CartUiState())
    val uiState = _uiState.asStateFlow()

    private var currentCartItems: List<Cart> = emptyList()
    private var currentMenuItems: List<MenuItem> = emptyList()

    private val tableId: Int = checkNotNull(
        savedStateHandle["tableId"]
    )

    companion object {
        private const val TAG = "CartViewModel"
    }

    init {
        Log.d(TAG, "tableId = $tableId")

        observeCarts()
        obverseMenuItems()
        loadTable()
    }

    private fun observeCarts() {
        viewModelScope.launch {
            cartRepository.getCarts(tableId).collect { items ->
                Log.d(TAG, "observeCarts: ${items.size} items -> $items")
                currentCartItems = items
                mapCartItems()
            }
        }
    }

    private fun obverseMenuItems() {
        viewModelScope.launch {
            menuItemRepository.getMenuItems().collect { items ->
                Log.d(TAG, "obverseMenuItems: ${items.size} items loaded")
                currentMenuItems = items
                mapCartItems()
            }
        }
    }

    private fun mapCartItems() {
        val uiModels = currentCartItems.mapNotNull { cartItem ->
            val menuItem = currentMenuItems.find {
                it.id == cartItem.menuItemId
            } ?: run {
                Log.d(TAG, "mapCartItems: menuItem NOT FOUND for menuItemId=${cartItem.menuItemId}")
                return@mapNotNull null
            }

            CartItemUiModel(
                draftOrderId = cartItem.tableId,
                name = menuItem.name,
                image = menuItem.image,
                price = menuItem.price,
                menuItemId = menuItem.id,
                quantity = cartItem.quantity
            )
        }

        val total = uiModels.sumOf { it.price * it.quantity }

        Log.d(TAG, "mapCartItems: mapped ${uiModels.size} items, total=$total")

        _uiState.update {
            it.copy(
                cartItemUiModel = uiModels,
                totalAmount = total
            )
        }
    }

    private fun loadTable() {
        viewModelScope.launch {
            val table = tableRepository.getTableById(tableId)
            Log.d(TAG, "loadTable: table=$table")
            _uiState.update {
                it.copy(
                    tableName = table?.name ?: "",
                    tableStatus = table?.status ?: TableStatus.EMPTY
                )
            }
        }
    }

    fun onIncreaseClick(menuItemId: Int) {
        Log.d(TAG, "onIncreaseClick: tableId=$tableId, menuItemId=$menuItemId")
        viewModelScope.launch {
            cartRepository.increase(tableId, menuItemId)
        }
    }

    fun onDecreaseClick(menuItemId: Int) {
        Log.d(TAG, "onDecreaseClick: tableId=$tableId, menuItemId=$menuItemId")
        viewModelScope.launch {
            cartRepository.decrease(tableId, menuItemId)
        }
    }

    fun onDeleteClick(menuItemId: Int) {
        Log.d(TAG, "onDeleteClick: tableId=$tableId, menuItemId=$menuItemId")
        viewModelScope.launch {
            cartRepository.delete(tableId, menuItemId)
        }
    }

    fun onCancelClick() {
        viewModelScope.launch {
            cartRepository.clear(tableId)
        }
    }

    fun placeOrder() {
        viewModelScope.launch {
            val cartItems = _uiState.value.cartItemUiModel
            val currentSession = sessionManager.session.first()

            if (cartItems.isEmpty()) {
                _uiState.update {
                    it.copy(
                        errorMessage = "Chưa có đồ uống trong giỏ hàng"
                    )
                }
                return@launch
            }

            if (!currentSession.isLoggedIn || currentSession.staffId == 0) {
                _uiState.update {
                    it.copy(
                        errorMessage = "Chưa đăng nhập hoặc phiên làm việc không hợp lệ"
                    )
                }
                return@launch
            }

            val staffId = currentSession.staffId

            try {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        errorMessage = null
                    )
                }

                val order = Order(
                    tableId = tableId,
                    status = OrderStatus.PREPARING,
                    staffId = staffId,
                    id = 0,
                    createdAt = LocalDateTime.now()
                )

                val orderId = orderRepository.insertOrder(order)

                cartItems.forEach { cartItem ->
                    val orderItem = OrderItem(
                        id = 0,
                        orderId = orderId.toInt(),
                        menuItemId = cartItem.menuItemId,
                        quantity = cartItem.quantity,
                        unitPrice = cartItem.price
                    )
                    orderItemRepository.insertOrderItem(orderItem)
                }

                cartRepository.clearCart()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null
                    )
                }
            } catch (exception: Exception) {
                Log.e(TAG, "placeOrder failed", exception)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Đặt hàng thất bại: ${exception.message ?: "Lỗi không xác định"}"
                    )
                }
            }
        }
    }
}