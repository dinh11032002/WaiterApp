package com.truongdinh.waiterapp.ui.features.order.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderDetailRoute(
    navController: NavController,
    viewModel: OrderDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    OrderDetailScreen(
        uiState = uiState,
        onBackClick = {
            navController.popBackStack()
        }
    )
}