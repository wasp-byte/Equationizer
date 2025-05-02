package com.waspbyte.equationizer

sealed class Screens(val route: String) {
    data object Home : Screens("home_screen")
    data object Classic : Screens("classic_screen")
}