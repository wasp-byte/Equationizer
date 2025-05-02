package com.waspbyte.equationizer

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.waspbyte.equationizer.screens.GameViewModel

@Composable
fun EquationText(viewModel: GameViewModel) {
    val equation = viewModel.currentEquation.collectAsState()
    Text(text = equation.value.puzzle)
}