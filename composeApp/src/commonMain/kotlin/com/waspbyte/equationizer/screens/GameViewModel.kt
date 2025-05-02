package com.waspbyte.equationizer.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waspbyte.equationizer.Puzzle
import com.waspbyte.equationizer.PuzzleResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.min

class GameViewModel: ViewModel() {
    val currentEquation = MutableStateFlow<Puzzle>(EquationPuzzle(1))

    private val _gameInput = MutableStateFlow<String>("")
    val gameInput = _gameInput.asStateFlow()

    val totalTime = 20000.0f
    var timeLeft = totalTime

    private val _progress = MutableStateFlow(1f)
    var progress = _progress.asStateFlow()

    fun startTimer() {
        viewModelScope.launch {
            while (true) {
                delay(100L)
                timeLeft -= 100.0f
                _progress.value = timeLeft / totalTime
            }
        }
    }

    fun onInputChange(newText: String) {
        when (currentEquation.value.check(newText)) {
            PuzzleResult.Wrong -> _gameInput.value = newText
            PuzzleResult.Finished -> {
                println("Finished")
                _gameInput.value = ""
                currentEquation.value = currentEquation.value.next()
                timeLeft = min(totalTime, timeLeft + 2000)
            }
            PuzzleResult.Correct -> _gameInput.value = ""
        }
    }
}