package com.waspbyte.equationizer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.waspbyte.equationizer.HIGH_SCORE_KEY
import com.waspbyte.equationizer.PrefsDataStore
import kotlinx.coroutines.flow.map

@Composable
fun GameEndScreen(level: Int, navController: NavHostController, prefs: PrefsDataStore) {
    val highScore = prefs.data.map { it[HIGH_SCORE_KEY] }.collectAsState(0)
    Column {
        Text("${level}lvl")
        Text("Highest: ${highScore.value ?: 0}")
        Row {
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
//            Button(onClick = { navController.navigate(Screens.)
        }
    }
}