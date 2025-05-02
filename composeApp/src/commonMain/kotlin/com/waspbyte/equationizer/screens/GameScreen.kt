package com.waspbyte.equationizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.waspbyte.equationizer.ClassicEquation
import com.waspbyte.equationizer.Equation
import com.waspbyte.equationizer.EquationText


@Composable
fun GameScreen(equation: Equation) {
    val viewModel = GameViewModel(equation)
    Column(
        modifier = Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {
        EquationText(viewModel)
        GetGameInput(viewModel)
    }
}

@Composable
fun GetGameInput(viewModel: GameViewModel) {
    val gameInput = viewModel.gameInput.collectAsState()
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        value = gameInput.value,
        onValueChange = { viewModel.onInputChange(it) },
        modifier = Modifier
            .focusRequester(focusRequester)
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

}