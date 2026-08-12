package com.truongdinh.waiterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.truongdinh.waiterapp.navigation.AppNavigation
import com.truongdinh.waiterapp.ui.theme.WaiterAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaiterAppTheme {
                AppNavigation()
            }
        }
    }
}