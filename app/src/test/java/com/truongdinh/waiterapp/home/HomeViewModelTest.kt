package com.truongdinh.waiterapp.home

import com.truongdinh.waiterapp.MainDispatcherRule
import com.truongdinh.waiterapp.data.local.session.SessionManager
import com.truongdinh.waiterapp.data.repository.TableRepository
import com.truongdinh.waiterapp.domain.model.Table
import com.truongdinh.waiterapp.domain.model.TableFilter
import com.truongdinh.waiterapp.domain.model.TableStatus
import com.truongdinh.waiterapp.ui.features.home.HomeViewModel
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
class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val tableRepository: TableRepository = mockk(relaxed = true)
    private val sessionManager: SessionManager = mockk(relaxed = true)
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(tableRepository, sessionManager)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun `load tables should update state with table`() = runTest {
        val tables = listOf(
            Table(
                id = 1,
                name = "Bàn 1",
                status = TableStatus.EMPTY
            ),
            Table(
                id = 2,
                name = "Bàn 2",
                status = TableStatus.EMPTY
            )
        )

        every {
            tableRepository.getTables()
        } returns flowOf(tables)

        viewModel = HomeViewModel(
            tableRepository,
            sessionManager
        )

        advanceUntilIdle()

        assertEquals(
            tables,
            viewModel.uiState.value.tables
        )
    }

    @Test
    fun `select empty filter should only empty tables`() = runTest {
        val tables = listOf(
            Table(
                id = 1,
                name = "Bàn 1",
                status = TableStatus.EMPTY
            ),
            Table(
                id = 2,
                name = "Bàn 2",
                status = TableStatus.SERVING
            )
        )

        every {
            tableRepository.getTables()
        } returns flowOf(tables)

        viewModel = HomeViewModel(
            tableRepository,
            sessionManager
        )

        advanceUntilIdle()

        viewModel.onSelectedChange(TableFilter.EMPTY)

        assertEquals(
            1,
            viewModel.uiState.value.tables.size
        )
    }

    @Test
    fun `search table by number should return matching table`() = runTest {
        val tables = listOf(
            Table(
                id = 1,
                name = "Bàn 1",
                status = TableStatus.EMPTY
            ),
            Table(
                id = 2,
                name = "Bàn 2",
                status = TableStatus.SERVING
            )
        )

        every {
            tableRepository.getTables()
        } returns flowOf(tables)

        viewModel = HomeViewModel(
            tableRepository,
            sessionManager
        )

        advanceUntilIdle()

        viewModel.onQueryChange("2")

        assertEquals(
            "Bàn 2",
            viewModel.uiState.value.tables.first().name
        )
    }
}