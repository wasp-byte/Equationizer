package com.waspbyte.equationizer.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.waspbyte.equationizer.ClassicEquation

@Composable
fun ClassicScreen(navController: NavHostController) {
    GameScreen(ClassicEquation())
}