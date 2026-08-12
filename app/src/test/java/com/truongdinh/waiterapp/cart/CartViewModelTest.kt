package com.truongdinh.waiterapp.cart

import androidx.lifecycle.SavedStateHandle
import com.truongdinh.waiterapp.MainDispatcherRule
import com.truongdinh.waiterapp.data.local.session.SessionManager
import com.truongdinh.waiterapp.data.repository.CartRepository
import com.truongdinh.waiterapp.data.repository.MenuItemRepository
import com.truongdinh.waiterapp.data.repository.OrderItemRepository
import com.truongdinh.waiterapp.data.repository.OrderRepository
import com.truongdinh.waiterapp.data.repository.TableRepository
import com.truongdinh.waiterapp.domain.model.Cart
import com.truongdinh.waiterapp.domain.model.MenuItem
import com.truongdinh.waiterapp.domain.model.Table
import com.truongdinh.waiterapp.domain.model.TableStatus
import com.truongdinh.waiterapp.ui.features.cart.CartViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CartViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val cartRepository: CartRepository = mockk(relaxed = true)
    private val menuItemRepository: MenuItemRepository = mockk(relaxed = true)
    private val tableRepository: TableRepository = mockk(relaxed = true)
    private val orderRepository: OrderRepository = mockk(relaxed = true)
    private val orderItemRepository: OrderItemRepository = mockk(relaxed = true)
    private val sessionManager: SessionManager = mockk(relaxed = true)
    private val savedStateHandle = SavedStateHandle(mapOf("tableId" to 1))

    private lateinit var cartViewModel: CartViewModel

    @Before
    fun setUp() {
        cartViewModel = CartViewModel(cartRepository, menuItemRepository,
            tableRepository, orderRepository, orderItemRepository, sessionManager, savedStateHandle)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `mapCartItems ghep dung ten va gia tu MenuItem`() = runTest {
        val cartItems = listOf(
            Cart(
                tableId = 1,
                menuItemId = 10,
                quantity = 2)
        )
        val menuItems = listOf(
            MenuItem(
                id = 10,
                name = "Cà phê sữa",
                price = 27000L,
                image = "",
                categoryId = 1,
                isAvailable = true
            )
        )

        every { cartRepository.getCarts(1) } returns flowOf(cartItems)
        every { menuItemRepository.getMenuItems() } returns flowOf(menuItems)
        coEvery { tableRepository.getTableById(1) } returns null

        val viewModel = CartViewModel(cartRepository, menuItemRepository,
            tableRepository, orderRepository, orderItemRepository, sessionManager, savedStateHandle)
        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertEquals(1, state.cartItemUiModel.size)
        assertEquals("Cà phê sữa", state.cartItemUiModel[0].name)
        assertEquals(54000L, state.totalAmount)
    }

    @Test
    fun `mapCartItems bo qua item khong tim thay MenuItem tuong ung`() = runTest {
        val cartItems = listOf(
            Cart(
                tableId = 1,
                menuItemId = 999,
                quantity = 1
            )
        )

        every { cartRepository.getCarts(1) } returns flowOf(cartItems)
        every { menuItemRepository.getMenuItems() } returns flowOf(emptyList())
        coEvery { tableRepository.getTableById(1) } returns null

        val viewModel = CartViewModel(cartRepository, menuItemRepository,
            tableRepository, orderRepository, orderItemRepository, sessionManager, savedStateHandle)
        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertEquals(0, state.cartItemUiModel.size)
        assertEquals(0L, state.totalAmount)
    }

    @Test
    fun `loadTable cap nhat dung ten va status cua ban`() = runTest {
        val table = Table(
            id = 1,
            name = "Bàn 1",
            status = TableStatus.SERVING
        )

        every { cartRepository.getCarts(1) } returns flowOf(emptyList())
        every { menuItemRepository.getMenuItems() } returns flowOf(emptyList())
        coEvery { tableRepository.getTableById(1) } returns table

        val viewModel = CartViewModel(cartRepository, menuItemRepository,
            tableRepository, orderRepository, orderItemRepository, sessionManager, savedStateHandle)
        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertEquals("Bàn 1", state.tableName)
        assertEquals(TableStatus.SERVING, state.tableStatus)
    }

    @Test
    fun `onIncreaseClick goi dung ham increase cua repository`() = runTest {
        every { cartRepository.getCarts(1) } returns flowOf(emptyList())
        every { menuItemRepository.getMenuItems() } returns flowOf(emptyList())
        coEvery { tableRepository.getTableById(1) } returns null

        val viewModel = CartViewModel(cartRepository, menuItemRepository,
            tableRepository, orderRepository, orderItemRepository, sessionManager, savedStateHandle)
        advanceUntilIdle()

        viewModel.onIncreaseClick(10)
        advanceUntilIdle()

        coVerify { cartRepository.increase(1, 10) }
    }

    @Test
    fun `onDecreaseClick goi dung ham decrease cua repository`() = runTest {
        every { cartRepository.getCarts(1) } returns flowOf(emptyList())
        every { menuItemRepository.getMenuItems() } returns flowOf(emptyList())
        coEvery { tableRepository.getTableById(1) } returns null

        val viewModel = CartViewModel(cartRepository, menuItemRepository,
            tableRepository, orderRepository, orderItemRepository, sessionManager, savedStateHandle)
        advanceUntilIdle()

        viewModel.onDecreaseClick(10)
        advanceUntilIdle()

        coVerify { cartRepository.decrease(1, 10) }
    }

    @Test
    fun `onDeleteClick goi dung ham delete cua repository`() = runTest {
        every { cartRepository.getCarts(1) } returns flowOf(emptyList())
        every { menuItemRepository.getMenuItems() } returns flowOf(emptyList())
        coEvery { tableRepository.getTableById(1) } returns null

        val viewModel = CartViewModel(cartRepository, menuItemRepository,
            tableRepository, orderRepository, orderItemRepository, sessionManager, savedStateHandle)
        advanceUntilIdle()

        viewModel.onDeleteClick(10)
        advanceUntilIdle()

        coVerify { cartRepository.delete(1, 10) }
    }

    @Test
    fun `onCancelClick goi dung ham clear cua repository`() = runTest {
        every { cartRepository.getCarts(1) } returns flowOf(emptyList())
        every { menuItemRepository.getMenuItems() } returns flowOf(emptyList())
        coEvery { tableRepository.getTableById(1) } returns null

        val viewModel = CartViewModel(cartRepository, menuItemRepository,
            tableRepository, orderRepository, orderItemRepository, sessionManager, savedStateHandle)
        advanceUntilIdle()

        viewModel.onCancelClick()
        advanceUntilIdle()

        coVerify { cartRepository.clear(1) }
    }
}