package com.truongdinh.waiterapp.ui.features.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.truongdinh.waiterapp.domain.model.TableStatus
import com.truongdinh.waiterapp.navigation.Screen

@Composable
fun HomeRoute(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        onTableClick = { table ->
            viewModel.updateTableStatus(table.id, TableStatus.SERVING)
            navController.navigate(
                Screen.Menu.createRoute(table.id)
            )
        },
        onSelectedChange = viewModel::onSelectedChange ,
        onQueryChange = viewModel::onQueryChange
    )
}