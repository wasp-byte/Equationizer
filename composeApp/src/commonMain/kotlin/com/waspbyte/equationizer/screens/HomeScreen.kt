package com.waspbyte.equationizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.waspbyte.equationizer.Screens

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(Modifier.fillMaxWidth().fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = { navController.navigate(Screens.Classic.route) }) {
            Text("Classic")
        }
        Button(onClick = { navController.navigate(Screens.Normal.route) }) {
            Text("Normal")
        }
        Button(onClick = { navController.navigate(Screens.Equation.route) }) {
            Text("Equation")
        }
    }
}