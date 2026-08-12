package com.truongdinh.waiterapp.ui.features.cart

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.truongdinh.waiterapp.navigation.Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CartRoute(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: CartViewModel = hiltViewModel()
) {
    val tableId = navBackStackEntry.arguments?.getInt("tableId") ?: 0

    val uiState by viewModel.uiState.collectAsState()

    CartScreen(
        onOrderClick = viewModel::placeOrder,
        onCancelClick = viewModel::onCancelClick,
        onClickBack = {
            navController.popBackStack()
        },
        onIncreaseClick = viewModel::onIncreaseClick,
        onDecreaseClick = viewModel::onDecreaseClick,
        onDeleteClick = viewModel::onDeleteClick,
        onItemClick = { item ->
            navController.navigate(
                Screen.Menu.createRoute(tableId, item.menuItemId)
            )
        },
        uiState = uiState
    )
}