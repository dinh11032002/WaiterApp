package com.truongdinh.waiterapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.truongdinh.waiterapp.data.local.session.SessionManager
import com.truongdinh.waiterapp.data.local.session.UserSession
import com.truongdinh.waiterapp.ui.features.auth.signin.SignInRoute
import com.truongdinh.waiterapp.ui.features.cart.CartRoute
import com.truongdinh.waiterapp.ui.features.home.HomeRoute
import com.truongdinh.waiterapp.ui.features.menu.MenuRoute
import com.truongdinh.waiterapp.ui.features.order.OrderRoute

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val context = LocalContext.current
    val sessionManager = remember {
        SessionManager(context)
    }

    val session by sessionManager.session.collectAsState(
        initial = UserSession()
    )

    val startDestination = if (session.isLoggedIn)
        Screen.Home.route
    else
        Screen.SignIn.route

    val currentScreen = listOf(
        Screen.Home,
        Screen.Profile,
        Screen.SignIn,
        Screen.Menu,
        Screen.Cart,
        Screen.Order
    ).find { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            if (currentScreen?.showBottomBar == true) {
                BottomBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            Modifier.padding(innerPadding)
        ) {
            composable(
                route = Screen.SignIn.route
            ) {
                SignInRoute(
                    navController = navController
                )
            }

            composable(
                route = Screen.Home.route
            ) {
                HomeRoute(
                    navController = navController
                )
            }

            composable(
                route = Screen.Menu.route,
                arguments = listOf(
                    navArgument("tableId") {
                        type = NavType.IntType
                    },
                    navArgument("replaceItemId") {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) { backStackEntry ->
                MenuRoute(
                    navController = navController,
                    navBackStackEntry = backStackEntry
                )
            }

            composable(
                Screen.Cart.route,
                arguments = listOf(
                    navArgument("tableId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                CartRoute(
                    navController = navController,
                    navBackStackEntry = backStackEntry
                )
            }

            composable(Screen.Order.route) {
                OrderRoute(
                    navController = navController
                )
            }

            composable(Screen.Profile.route) {

            }
        }
    }
}