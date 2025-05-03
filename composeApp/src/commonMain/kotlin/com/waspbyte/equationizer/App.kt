package com.waspbyte.equationizer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.waspbyte.equationizer.screens.ClassicScreen
import com.waspbyte.equationizer.screens.EquationScreen
import com.waspbyte.equationizer.screens.GameEndScreen
import com.waspbyte.equationizer.screens.HomeScreen
import com.waspbyte.equationizer.screens.NormalScreen
import com.waspbyte.equationizer.ui.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(prefs: PrefsDataStore) {
    MaterialTheme {
        val navController = rememberNavController()
        AppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

                NavHost(
                    navController = navController,
                    startDestination = Screens.Home.route,
                ) {
                    composable(Screens.Home.route) {
                        HomeScreen(navController, prefs)
                    }
                    composable(Screens.Classic.route) {
                        ClassicScreen(navController, prefs)
                    }
                    composable(Screens.Normal.route) {
                        NormalScreen(navController, prefs)
                    }
                    composable(Screens.Equation.route) {
                        EquationScreen(navController, prefs)
                    }
                    composable<Screens.GameEnd> { backStackEntry ->
                        val entry = backStackEntry.toRoute<Screens.GameEnd>()
                        GameEndScreen(entry.level, entry.fromRoute, navController, prefs)
                    }
                }
            }
        }
    }
}