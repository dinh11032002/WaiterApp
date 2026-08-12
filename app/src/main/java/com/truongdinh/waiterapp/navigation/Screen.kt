package com.truongdinh.waiterapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null,
    val showBottomBar: Boolean = false
) {
    data object Home : Screen(
        route = "home",
        title = "Trang chủ",
        icon = Icons.Default.Home,
        showBottomBar = true
    )

    data object Profile : Screen(
        route = "profile",
        title = "Cá nhân",
        icon = Icons.Default.Person,
        showBottomBar = true
    )

    data object SignIn : Screen(route = "signin")

    data object Menu : Screen(route = "menu/{tableId}?replaceItemId={replaceItemId}") {
        fun createRoute(tableId: Int, replaceItemId: Int? = null) =
            if (replaceItemId == null)
                "menu/$tableId"
            else {
                "menu/$tableId?replaceItemId=$replaceItemId"
            }
    }

    data object Cart : Screen(route = "cart/{tableId}") {
        fun createRoute(tableId: Int) =
            "cart/$tableId"
    }

    data object Order : Screen(
        route = "order",
        title = "Đơn hàng",
        icon = Icons.Default.Receipt,
        showBottomBar = true
    )
}