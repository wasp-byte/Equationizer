package com.waspbyte.equationizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.lifecycle.viewmodel.compose.viewModel
import com.waspbyte.equationizer.EquationText
import com.waspbyte.equationizer.Puzzle


@Composable
fun GameScreen(puzzle: Puzzle, viewModel: GameViewModel = viewModel()) {
    val progress by viewModel.progress.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startTimer()
        viewModel.currentEquation.value = puzzle
    }

    Column(
        modifier = Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )
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
