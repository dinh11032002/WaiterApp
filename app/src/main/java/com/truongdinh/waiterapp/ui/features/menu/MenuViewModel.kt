package com.truongdinh.waiterapp.ui.features.menu

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truongdinh.waiterapp.data.repository.CategoryRepository
import com.truongdinh.waiterapp.data.repository.CartRepository
import com.truongdinh.waiterapp.data.repository.MenuItemRepository
import com.truongdinh.waiterapp.data.repository.TableRepository
import com.truongdinh.waiterapp.domain.model.Cart
import com.truongdinh.waiterapp.domain.model.MenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val menuItemRepository: MenuItemRepository,
    private val tableRepository: TableRepository,
    private val cartRepository: CartRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(MenuUiState())
    val uiState = _uiState.asStateFlow()

    private val tableId: Int = checkNotNull(
        savedStateHandle["tableId"]
    )

    private val replaceItemId = savedStateHandle.get<Int>("replaceItemId")?.takeIf { it != -1 }

    private var filterJob: Job? = null
    private var searchJob: Job? = null
    private var cartJob: Job? = null

    companion object {
        private const val TAG = "MenuViewModel"
    }

    init {
        observeCategories()
        syncCategories()
        observeMenuItems()
        syncMenuItems()
        loadTable()
        observeCart()
        syncCart()

        Log.d(TAG, "Received tableId = $tableId")

        Log.d(TAG, "replaceItemId = $replaceItemId")
    }

    private fun observeCategories() {
        viewModelScope.launch {
            categoryRepository.getCategories()
                .collect { categories ->
                    _uiState.update {
                        it.copy(
                            categories = categories
                        )
                    }
                }
        }
    }

    private fun syncCategories() {
        viewModelScope.launch {
            categoryRepository.syncCategories()
        }
    }

    private fun observeMenuItems() {
        viewModelScope.launch {
            menuItemRepository.getMenuItems()
                .collect { menuItems ->
                    _uiState.update { currentState ->
                        val newState = currentState.copy(
                            allMenuItems = menuItems
                        )
                        if (currentState.selectedCategory == null &&
                            currentState.menuSearch.isEmpty()) {
                            newState.copy(menuItems = menuItems)
                        } else {
                            newState
                        }
                    }
                    refreshTotalPrice()
                }
        }
    }

    private fun syncMenuItems() {
        viewModelScope.launch {
            menuItemRepository.syncMenuItems()
        }
    }

    private fun loadTable() {
        viewModelScope.launch {
            val table = tableRepository
                .getTableById(tableId)

            _uiState.update {
                it.copy(
                    tableName = table!!.name
                )
            }
        }
    }

    fun searchMenuItem(query: String) {
        searchJob?.cancel()

        Log.d(
            TAG,
            "Search query: $query"
        )

        _uiState.update {
            it.copy(
                menuSearch = query
            )
        }

        searchJob = viewModelScope.launch {
            menuItemRepository
                .searchMenuItem(query)
                .collect { items -> // chỗ này cũng tương tự như hàm dưới
                    Log.d(
                        TAG,
                        "Found ${items.size} items"
                    )

                    _uiState.update {
                        it.copy(
                            menuItems = items
                        )
                    }
                }
        }
    }

    fun filterMenuItemsByCategory(categoryId: Int?) {
        filterJob?.cancel()

        if (categoryId == null) {
            Log.d(
                TAG,
                "Filter category: $categoryId"
            )

            _uiState.update {
                it.copy(
                    selectedCategory = null,
                    menuItems = it.allMenuItems
                )
            }

            return
        }

        filterJob = viewModelScope.launch {
            menuItemRepository
                .getMenuItemByCategory(categoryId)
                .collect { item ->
                    // collect không phải nguyên nhân trực tiếp gây lỗi.
                    // Vấn đề là mỗi lần chọn category lại tạo một coroutine mới,
                    // dẫn đến nhiều collector cùng hoạt động và cùng cập nhật uiState
                    Log.d(
                        TAG,
                        "Found ${item.size} items"
                    )

                    _uiState.update {
                        it.copy(
                            selectedCategory = categoryId,
                            menuItems = item
                        )
                    }
                }
        }
    }

    fun addToCart(menuItem: MenuItem) {
        viewModelScope.launch {
            if (replaceItemId == null) {
                cartRepository
                    .add(
                        menuItemId = menuItem.id,
                        tableId = tableId
                    )
            } else {
                cartRepository.replaceDrink(
                    tableId,
                    replaceItemId,
                    menuItem.id
                )
                savedStateHandle["replaceItemId"] = null
            }
        }
    }

    private fun updateCartInfo(orderItems: List<Cart>) {
        val totalItems =
            orderItems
                .filter {
                    it.tableId == tableId
                }
                .sumOf {
                it.quantity
            }

        val totalPrices =
            orderItems
                .filter {
                    it.tableId == tableId
                }
                .sumOf { orderItem ->
                val menuItem =
                    _uiState.value.allMenuItems.find {
                        it.id == orderItem.menuItemId
                    }

                val itemTotal =
                    (menuItem?.price ?: 0L) * orderItem.quantity

                Log.d(
                    TAG,
                    "Calculate: ${menuItem?.name} | price=${menuItem?.price} | quantity=${orderItem.quantity} | total=$itemTotal"
                )

                itemTotal
            }

        Log.d(
            TAG,
            "Cart Summary -> totalItems=$totalItems | totalPrices=$totalPrices"
        )

        _uiState.update {
            it.copy(
                totalItems = totalItems,
                totalPrices = totalPrices
            )
        }
    }

    private fun observeCart() {
        cartJob?.cancel()

        cartJob = viewModelScope.launch {
            cartRepository
                .getCarts(tableId)
                .collect { cartItems ->
                Log.d(TAG, "observeDraftOrder: ${cartItems.size}")
                updateCartInfo(cartItems)
            }
        }
    }

    private fun syncCart() {
        viewModelScope.launch {
            cartRepository.syncCart(tableId)
        }
    }

    private fun refreshTotalPrice() {
        viewModelScope.launch {
            val cartItems = cartRepository.getCarts(tableId).first()
            updateCartInfo(cartItems)
        }
    }
}