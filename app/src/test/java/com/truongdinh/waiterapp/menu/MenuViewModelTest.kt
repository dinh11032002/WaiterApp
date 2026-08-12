package com.truongdinh.waiterapp.menu

import androidx.lifecycle.SavedStateHandle
import com.truongdinh.waiterapp.MainDispatcherRule
import com.truongdinh.waiterapp.data.repository.CategoryRepository
import com.truongdinh.waiterapp.data.repository.CartRepository
import com.truongdinh.waiterapp.data.repository.MenuItemRepository
import com.truongdinh.waiterapp.data.repository.TableRepository
import com.truongdinh.waiterapp.data.source.ImageSource
import com.truongdinh.waiterapp.domain.model.MenuItem
import com.truongdinh.waiterapp.ui.features.menu.MenuViewModel
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

class MenuViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val categoryRepository: CategoryRepository = mockk(relaxed = true)
    private val menuItemRepository: MenuItemRepository = mockk(relaxed = true)
    private val tableRepository: TableRepository = mockk(relaxed = true)
    private val cartRepository: CartRepository = mockk(relaxed = true)
    private val savedStateHandle = SavedStateHandle(mapOf("tableId" to 1))

    private lateinit var menuViewModel: MenuViewModel

    @Before
    fun setUp() {
        menuViewModel = MenuViewModel(categoryRepository, menuItemRepository, tableRepository,
            cartRepository, savedStateHandle)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchMenuItem_updatesMenuItems() = runTest {
        val menuItems = listOf(
            MenuItem(
                id = 1,
                name = "Cà phê đen",
                price = 25000L,
                image = ImageSource.BLACK_COFFEE,
                categoryId = 1,
                isAvailable = true
            ),

            MenuItem(
                id = 8,
                name = "Trà đào cam sả",
                price = 35000L,
                image = ImageSource.PEACH_ORANGE_LEMONGRASS_TEA,
                categoryId = 2,
                isAvailable = true
            )
        )

        every {
            menuItemRepository.searchMenuItem("Cà phê đen")
        } returns flowOf(
            listOf(
                menuItems[0]
            )
        )

        menuViewModel.searchMenuItem("Cà phê đen")

        advanceUntilIdle()

        assertEquals(
            "Cà phê đen",
            menuViewModel.uiState.value.menuSearch
        )
    }
}