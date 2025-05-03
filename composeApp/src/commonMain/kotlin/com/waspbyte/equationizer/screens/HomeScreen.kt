package com.waspbyte.equationizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavHostController
import com.waspbyte.equationizer.HIGH_SCORE_KEY
import com.waspbyte.equationizer.PrefsDataStore
import com.waspbyte.equationizer.Screens
import com.waspbyte.equationizer.TIMER_KEY
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, prefs: PrefsDataStore) {
    val checked = prefs.data.map { it[TIMER_KEY] }.collectAsState(true)
    val highScore = prefs.data.map { it[HIGH_SCORE_KEY] }.collectAsState(0)
    val scope = rememberCoroutineScope()
    Column(horizontalAlignment = Alignment.End) {
        Switch(
            checked = checked.value ?: true,
            onCheckedChange = { newChecked ->
                scope.launch {
                    prefs.edit { it[TIMER_KEY] = newChecked }
                }
            }
        )
        Column(
            Modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { navController.navigate(Screens.Classic.route) }) {
                Text("Classic")
            }
            Text("High score: ${highScore.value}")
            Button(onClick = { navController.navigate(Screens.Normal.route) }) {
                Text("Normal")
            }
            Button(onClick = { navController.navigate(Screens.Equation.route) }) {
                Text("Equation")
            }
        }
    }
}