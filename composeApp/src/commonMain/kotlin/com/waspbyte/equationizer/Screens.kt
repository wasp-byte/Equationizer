package com.waspbyte.equationizer

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens(val route: String) {
    data object Home : Screens("home_screen")
    data object Classic : Screens("classic_screen")
    data object Normal : Screens("normal_screen")
    data object Equation : Screens("equation_screen")
    @Serializable
    data class GameEnd(val level: Int, val fromRoute: String) : Screens("game_end_screen")
}