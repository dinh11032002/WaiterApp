package com.truongdinh.waiterapp.ui.features.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.truongdinh.waiterapp.navigation.Screen

@Composable
fun MenuRoute(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val tableId = navBackStackEntry.arguments?.getInt("tableId") ?: 0

    val uiState by viewModel.uiState.collectAsState()

    MenuScreen(
        uiState = uiState,
        onBackClick = {
            navController.popBackStack()
        },
        onMenuSearchChange = viewModel::searchMenuItem,
        onSelectedChange = viewModel::filterMenuItemsByCategory,
        onMenuItemClick = viewModel::addToCart,
        onBottomCartBarClick = {
            navController.navigate(
                Screen.Cart.createRoute(tableId)
            )
        }
    )
}