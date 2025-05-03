package com.waspbyte.equationizer.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.waspbyte.equationizer.EquationText
import com.waspbyte.equationizer.PrefsDataStore
import com.waspbyte.equationizer.Puzzle

@Composable
fun GameScreen(puzzle: Puzzle, prefs: PrefsDataStore, viewModel: GameViewModel = viewModel(factory = GameVMFactory(prefs))) {
    val progress by viewModel.progress.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startTimer()
        viewModel.currentEquation.value = puzzle
    }

    Box(modifier = Modifier.fillMaxSize()) {
        RectangularProgressBar (progress = progress)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EquationText(viewModel)
            GetGameInput(viewModel)
        }
    }
}

@Composable
fun GetGameInput(viewModel: GameViewModel) {
    val gameInput = viewModel.gameInput.collectAsState()
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        value = gameInput.value,
        onValueChange = { viewModel.onInputChange(it) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .focusRequester(focusRequester)
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
fun RectangularProgressBar(progress: Float) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val strokeWidth = 20.dp.toPx()
        val width = size.width
        val height = size.height

        val perimeter = 2 * (width + height)

        val emptyLength = perimeter * (1f - progress)

        var remainingEmptyLength = emptyLength

            val topEmpty = minOf(remainingEmptyLength, width)
            drawLine(
                color = Color.Red,
                start = androidx.compose.ui.geometry.Offset(0f, 0f),
                end = androidx.compose.ui.geometry.Offset(width - topEmpty, 0f),
                strokeWidth = strokeWidth
            )
            remainingEmptyLength -= topEmpty

            val leftEmpty = minOf(remainingEmptyLength, height)
            drawLine(
                color = Color.Red,
                start = androidx.compose.ui.geometry.Offset(0f, height),
                end = androidx.compose.ui.geometry.Offset(0f, leftEmpty),
                strokeWidth = strokeWidth
            )
            remainingEmptyLength -= leftEmpty

            val bottomEmpty = minOf(remainingEmptyLength, width)
            drawLine(
                color = Color.Red,
                start = androidx.compose.ui.geometry.Offset(bottomEmpty, height),
                end = androidx.compose.ui.geometry.Offset(width, height),
                strokeWidth = strokeWidth
            )
            remainingEmptyLength -= bottomEmpty

            val rightEmpty = minOf(remainingEmptyLength, height)
            drawLine(
                color = Color.Red,
                start = androidx.compose.ui.geometry.Offset(width, 0f),
                end = androidx.compose.ui.geometry.Offset(width, height - rightEmpty),
                strokeWidth = strokeWidth
            )
            remainingEmptyLength -= rightEmpty
    }
}