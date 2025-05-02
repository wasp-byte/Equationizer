package com.waspbyte.equationizer

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.waspbyte.equationizer.screens.ClassicScreen
import com.waspbyte.equationizer.screens.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
        ) {
            composable(Screens.Home.route) {
                HomeScreen(navController)
            }
            composable(Screens.Classic.route) {
                ClassicScreen(navController)
            }
        }
    }
}